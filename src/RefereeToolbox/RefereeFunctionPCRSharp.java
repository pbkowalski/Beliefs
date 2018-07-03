/*
 *   RefereeFunctionPCRSharp.java : part of package RefereeToolbox;
 *          Implementation of referee function.
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
 * This class implements the Referee Function encoding the PCRSharp combination rule.
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
public class RefereeFunctionPCRSharp<Prop extends Lattice<Prop> >
                                  extends RefereeFunctionDefault<Prop> {
    protected int theEntrySize=-1;
    protected Prop theDefault=null;
    protected Prop theZero=null;
    protected Prop theOne=null;
    protected  boolean[] theMask=null;
    protected  boolean[] theRevMask=null;

    protected  double[] weightTab=null;
    protected  int[] mapTab=null;
    protected ArrayList<Prop> thePropositionTab=null;

    protected  ArrayList<Assignment<Prop>> getWeightedSubset(int subsetSize,
                                        ArrayList<Assignment<Prop>> assignIn) {
        double theNorm;
        int j,k,pivot;
        boolean justMoved;
        ArrayList<Assignment<Prop>> theAssignmentList;
        theAssignmentList = new ArrayList<Assignment<Prop>>();
        Assignment<Prop> curAssignment;
        Prop aProposition;
        double aValue;

        // init
        theNorm=0.;
        pivot=subsetSize;
        weightTab[pivot]=1.;
        thePropositionTab.set(pivot, theOne);
        mapTab[pivot]=theEntrySize;
        justMoved=true;
        // init done

        // loop
        do {
            if(justMoved) {
                justMoved=false;
                for(j=pivot;j>0;) {
                    k=j;
                    j--;
                    mapTab[j]=j;
                    weightTab[j]=weightTab[k]*assignIn.get(j).value;
                    thePropositionTab.set(j, theZero.instanceNsize().and(
                       assignIn.get(j).attribute, thePropositionTab.get(k)));
                    pivot--;
                }
                // build a new assignment?
                aProposition=thePropositionTab.get(0);
                if(aProposition.compareTo(theZero)!=0) {
                    // a new assignment is created for the next case
                    aValue=weightTab[0];
                    curAssignment = new Assignment<Prop>();
                    curAssignment.attribute=aProposition;
                    curAssignment.value=aValue;
                    theAssignmentList.add(curAssignment);
                    theNorm+=aValue;
                }
            }
            k=pivot+1;
            j=mapTab[pivot]+1;
            if(j<mapTab[k]) {
                mapTab[pivot]=j;
                weightTab[pivot]=weightTab[k]*assignIn.get(j).value;
                thePropositionTab.set(pivot, theZero.instanceNsize().and(
                       assignIn.get(j).attribute, thePropositionTab.get(k)));
                justMoved=true;
            } else pivot++;

        } while(pivot<subsetSize);

        for(j=0;j<theAssignmentList.size();j++) theAssignmentList.get(j).value/=theNorm;

        return theAssignmentList;
    }

    
    protected ArrayList<Assignment<Prop>> askWeightedSubset(int subsetSize,
                                         ArrayList<Assignment<Prop>> assignIn) {
                  // Test the mask in order to decide for the computation
        int k;
        if(theMask==null) {
            if(theRevMask==null) return getWeightedSubset(subsetSize,assignIn);
        } else {
            if((k=(subsetSize-1))<theMask.length)
                    if(theMask[k]) return getWeightedSubset(subsetSize,assignIn);
        }
        if(theRevMask!=null) {
            if((k=(theEntrySize-subsetSize))<theRevMask.length)
                    if(theRevMask[k]) return getWeightedSubset(subsetSize,assignIn);
        }
        return new ArrayList<Assignment<Prop>>();
    }


    //////////////////////////////////////////////////:
    // public part
    ///////////////

/**
 * Set the default answer to proposition <i>aDefault</i>.
 * The default answer is returned by the PCRSharp referee function, when no consensus
 * have been found among the acceptable subset combination of sources.
 * <BR><BR>
 * The default answer is initialized to <i>null</i> at the class construction,
 * which is automatically handled as a <i>zero</i>.
 *
 */
    public void setDefaultAnswer(Prop aDefault) {
        if(aDefault==null) {
           System.err.println("Error :: "+this.getClass().getName() +
                ".setDefaultAnswer(Prop)");
            System.err.println("Error :: aDefault is null");
            System.exit(0);
        }
        theDefault=aDefault.clone();
    }

/**
 * Set a mask and a reverse mask which will select the sizes of subset combination
 * (of the sources entries) which are taken into consideration.
 * These parameters work as follows:
 * <br><br>
 * If <i>aMask=null</i> and <i>aRevMask=null</i>\,, then all subset combinations are
 * managed (this is a dangerous choice, when there are many sources).
 * <br><br>Otherwise:
 * <br>
 * If <i>aMask=null</i>, then <i>aMask</i> is not considered for masking.
 * <br>
 * If <i>aRevMask=null</i>, then <i>aRevMask</i> is not considered for masking.
 * <br><br>
 * aMask defined the clearance where it is <i>true</i> from (minimal) subset size
 * 1 upward
 * <br>
 * aRevMask defined the clearance where it is <i>true</i> from (maximal) subset
 * size <i>theEntrySize</i> downward
 *
 */
    public void setMask(boolean[] aMask, boolean[] aRevMask) {
        // NB: null Mask means that the mask is not considered
        // null & null masks means that there is no masking
        // aMask => define clearance from (minimal) set size 1 upward
        // aRevMask => define clearance from (maximal) set size theEntrySize downward
        int i;
        theMask=new boolean[aMask.length];
        theRevMask=new boolean[aRevMask.length];
        for(i=0;i<theMask.length;i++) theMask[i]=aMask[i];
        for(i=0;i<theRevMask.length;i++) theRevMask[i]=aRevMask[i];
    }

    @Override
    public ArrayList<Assignment<Prop>> refereeFunction(ArrayList<Assignment<Prop>> assignIn,
                                                     ArrayList<minAssignment<Prop>> bbaIn) {
        if(assignIn==null) {
            System.err.println("Error :: "+this.getClass().getName() +
                ".refereeFunction(ArrayList<Assignment<Prop>>,ArrayList<B>)");
            System.err.println("Error :: assignIn is null");
            System.exit(0);
        }
        int i;
        i=assignIn.size();
        if(i<1) {
            System.err.println("Error :: "+this.getClass().getName() +
                ".refereeFunction(ArrayList<Assignment<Prop>>,ArrayList<B>)");
            System.err.println("Error :: assignIn is empty");
            System.exit(0);
        }
        if(theEntrySize!=i) {
            theEntrySize=i;
            weightTab = new double[theEntrySize+1];
            mapTab= new int[theEntrySize+1];
            thePropositionTab = new ArrayList<Prop>();
            for(i=0;i<=theEntrySize;i++) {
                thePropositionTab.add(null);
            }
        }
        if(theZero==null) {
            theZero=assignIn.get(0).attribute.instanceNsize().zero();
            theOne=theZero.instanceNsize().one();
        }
        if(theDefault==null) theDefault=theZero;
        ArrayList<Assignment<Prop>> arbitrament=null;
        for(i=theEntrySize;i>0;i--) {
            arbitrament=askWeightedSubset(i, assignIn);
            if(arbitrament.size()>0) return arbitrament;
        }
        Assignment<Prop> finalAssignment = new Assignment<Prop>();
        finalAssignment.attribute=theDefault.clone();
        finalAssignment.value=1.;
        // NB: initialization of arbitrament is done in the loop
        arbitrament.add(finalAssignment);
        return arbitrament;
    }

}
