/*
 *   TreeSetRefereeFuser.java : part of package RefereeToolbox; Implementation
 *          of fuser based on referee function.
 *******************************************************************************
 *   Copyright (c) 2010 Frédéric Dambreville
 *
 *   Author:    Frédéric Dambreville <http://email.fredericdambreville.com>
 *
 *   This file is part of RefereeToolbox.
 *
 *   RefereeToolbox is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   RefereeToolbox is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with RefereeToolbox. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************
 */

package RefereeToolbox;

import java.util.*;

/**
 * This class implements a generic combination of Basic Belief Assignments by the
 * means of referee functions and on the basis of an exact computation. This
 * class does not handle direct rule implementations, {@link BBAFuser}, or
 * implementations based on referee sampling, {@link SampledBBARefereeFuser}.
 *
 * @author      <A href="http://email.fredericdambreville.com">Frédéric Dambreville</A>
 *
 *
 *
 * <BR><BR><table border='1' cellPadding='4'><tr><td>
 * <font color="#008000" style="font-family: georgia">
 *   Copyright (c) 2010 Frédéric Dambreville<BR>
 * <BR>
 *   RefereeToolbox is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.<BR>
 * <BR>
 *   RefereeToolbox is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.<BR>
 * <BR>
 *   You should have received a copy of the GNU General Public License
 *   along with RefereeToolbox. If not, see
 * <a href='http://www.gnu.org/licenses/'>http://www.gnu.org/licenses/</a>.
 * </font></table><BR>
 *
 */

public class TreeSetRefereeFuser<Prop extends Lattice<Prop>,
            B extends TreeSetRefereeFuser<Prop,B>  >
        extends TreeSetBBA<Prop,B>
        implements BBARefereeFuser<Prop, B> {

    protected double theConflict=-1.;
    protected Prop theZero=null;
    protected ArrayList<minAssignment<Prop>> BBASources= new ArrayList<minAssignment<Prop>>();


    //////////////////////////////////////////////////:
    // public part
    ///////////////

    public double conflict() {
        return theConflict;
    }

    public B fuse(B left, B right, RefereeFunctionDefault<Prop> theRefereeFunction) {
        ArrayList<B> bbaTab= new ArrayList<B>();
        bbaTab.add(left);
        bbaTab.add(right);
        return fuse(bbaTab, theRefereeFunction);
    }
    /**
     * Return the BBA containing only the focal sets which intersect <i>aProposition</i>. The remaining mass is redistributed across the remaining focal sets.
     * @param aProposition
     * @return
     */
    public B plausibilityMass(Prop aProposition){
    	B plausMass = this.instance();
    	double Plaus = this.Pl(aProposition);
        for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                it.hasNext(); ) {
        	Assignment<Prop> currentPair=it.next();
        	if(currentPair.attribute.intersects(aProposition))
                plausMass.add(currentPair.attribute, currentPair.value/Plaus);
        }
    	return plausMass;
    }
    /**
     * Return the BBA containing only the focal sets which are supersets of <i>aProposition</i>. The remaining mass is assigned to the empty set
     * @param aProposition
     * @return
     */
    public B conditionedMassWithEmptySet(Prop aProposition){
    	B conditionedMass = this.instance();
    	double Plaus = this.Pl(aProposition);
        for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                it.hasNext(); ) {
        	Assignment<Prop> currentPair=it.next();
        	if(currentPair.attribute.contains(aProposition))
        		conditionedMass.add(currentPair.attribute, currentPair.value);
        }
        Prop emptySet = bbaTreeA.first().attribute.instanceNsize().zero();
        conditionedMass.add(emptySet, Plaus);
    	return conditionedMass;
    }
    public B fuse(ArrayList<B> bbaIn, RefereeFunctionDefault<Prop> theRefereeFunction) {
        int nbIn;
        int k,l;
        double Norm;
        nbIn=bbaIn.size();
        if(nbIn<1) return null;
        Iterator[] j=new Iterator[nbIn];
        ArrayList<Assignment<Prop>> assignIn = new ArrayList<Assignment<Prop>>();
        BBASources.clear();
        for(k=0;k<nbIn;k++) {
            assignIn.add(null);
            BBASources.add(bbaIn.get(k));
        } // Chercher mieux ?
        //
        double[] propEval= new double[1+nbIn];
        propEval[nbIn]=1.;
        ArrayList<Assignment<Prop>> refOut;
        Assignment<Prop> aTmpAssign;
        B bbaResult= instance();
        bbaResult.clear();
        bbaResult.maxSize(this.theMaxSize);
        for(k=0;k<nbIn;k++) j[k]=bbaIn.get(k).bbaTreeVA.iterator();
        for(k=nbIn-1;k<nbIn;) {
            if(j[k].hasNext()) {
                aTmpAssign=(Assignment<Prop>) j[k].next();
                assignIn.set(k, aTmpAssign);
                propEval[k]=propEval[k+1]*aTmpAssign.value;
                if(k==0) {
                    refOut=theRefereeFunction.refereeFunction(assignIn,BBASources);
                    for(l=0;l<refOut.size();l++)
                        bbaResult.add(refOut.get(l).attribute,
                                    propEval[0]*refOut.get(l).value);
                } else {
                    k--;
                }
            } else {
                j[k]=bbaIn.get(k).bbaTreeVA.iterator();
                k++;
            }
        }
        bbaResult.update_notification();
        Norm=0.;
        if(theZero==null)
                    theZero=bbaResult.bbaTreeA.first().attribute.instanceNsize().zero();
        theConflict=bbaResult.remove(theZero);
        for (Iterator<Assignment<Prop>> it = bbaResult.bbaTreeA.iterator();
                                            it.hasNext(); ) {
            Assignment<Prop> currentPair=it.next();
            Norm+=currentPair.value;
        }
        clear();
        for (Iterator<Assignment<Prop>> it = bbaResult.bbaTreeA.iterator();
                                            it.hasNext(); ) {
            Assignment<Prop> currentPair=it.next();
            currentPair.value/=Norm;
            add(currentPair);
        }
        update_notification();

        return (B) this;
    }

}

