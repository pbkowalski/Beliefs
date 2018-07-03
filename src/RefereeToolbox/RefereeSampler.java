/*
 *   RefereeSampler.java : part of package RefereeToolbox; Implementation of
 *          sampling-based fuser by means of referee function.
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
 * means of referee functions and on the basis of a particle approximation. This
 * class does not handle direct rule implementations, {@link BBAFuser}, or exact
 * referee-based fusion {@link BBARefereeFuser}.
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
public class RefereeSampler<Prop extends Lattice<Prop>,
                                        sB extends RefereeSampler<Prop,sB> >
                                            extends TreeSetBBA<Prop,sB>
                                            implements SampledBBARefereeFuser<Prop,sB> {

    protected Prop theZero=null;

    protected RefereeFunctionDefault<Prop> theRefereeFunction;

    protected static Random rng = new Random();

    protected boolean __updated=true;

    protected double[] cumulativeDistribution_Sampler=null; // cumulative distribbution is used
                                          // for sampling
    protected double[] cumulativeDistribution_Mixer=null; // cumulative distribbution is used
                                          // for sampling

    protected ArrayList<Assignment<Prop>> mappedAttribute;

    protected ArrayList<Assignment<Prop>> answeredAttribute;

    protected int[] jumpTable_Sampler=null; // used for ensuring fast sampling

    protected int[] jumpTable_Mixer=null; // used for ensuring fast sampling

    protected ArrayList<minAssignment<Prop>> BBASources= new ArrayList<minAssignment<Prop>>();

    protected ArrayList<sB> fuserSources;

    protected ArrayList<sB> mixerSources;


    protected boolean setTables_sampler() {
        Iterator iter = bbaTreeVA.iterator();
        int size=bbaTreeVA.size();
        cumulativeDistribution_Sampler=new double[size];
        jumpTable_Sampler=new int[size];
        mappedAttribute= new ArrayList<Assignment<Prop>>();
        Assignment<Prop> assignment;
        int i; // true variable
        int k; // uniform variable
        double Cumul=0.; // For computing law cumulatives
        double uStep=1./size; // uniform step

        for(i=0; iter.hasNext(); i++) { // compute the cumulative of inDist
                assignment=(Assignment<Prop>) iter.next();
                mappedAttribute.add(assignment);
                Cumul+=assignment.value;
                cumulativeDistribution_Sampler[i]=Cumul;
        }
        for(i=0; i<size; i++) cumulativeDistribution_Sampler[i]/=Cumul; // normalize it
        jumpTable_Sampler[0]=(i=0);
        Cumul=uStep;
        for(k=1;k<size;k++) {
            while(cumulativeDistribution_Sampler[i]<=Cumul) i++;
            jumpTable_Sampler[k]=i;
            Cumul+=uStep;
        }
        return true;
    }

    protected boolean setTables_mixer(double[] inDist) {
        int i; // true variable
        int k; // uniform variable
        double Cumul=0.; // For computing law cumulatives
        double uStep=1./inDist.length; // uniform step
        cumulativeDistribution_Mixer=new double[inDist.length];
        jumpTable_Mixer=new int[inDist.length];
        for(i=0; i<inDist.length; i++) { // compute the cumulative of inDist
                Cumul+=inDist[i];
                cumulativeDistribution_Mixer[i]=Cumul;
        }
        for(i=0; i<inDist.length; i++)
                cumulativeDistribution_Mixer[i]/=Cumul; // normalize it
        jumpTable_Mixer[0]=(i=0);
        Cumul=uStep;
        for(k=1;k<inDist.length;k++) {
            while(cumulativeDistribution_Mixer[i]<=Cumul) i++;
            jumpTable_Mixer[k]=i;
            Cumul+=uStep;
        }
        return true;
    }

    protected int drawTable_sampler() {
        if(__updated) if(!setTables_sampler()) return -1;
        double x=rng.nextDouble();
        int i=jumpTable_Sampler[(int) (jumpTable_Sampler.length*x)];
        while (x>=cumulativeDistribution_Sampler[i]) i++;
        return i;
    }

    protected int drawTable_mixer() {
        double x=rng.nextDouble();
        int i=jumpTable_Mixer[(int) (jumpTable_Mixer.length*x)];
        while (x>=cumulativeDistribution_Mixer[i]) i++;
        return i;
    }


    //////////////////////////////////////////////////:
    // public part
    ///////////////

    @Override
    public void update_notification() { // called each time a change is done
        super.update_notification();
        __updated=true;
    }

    public double learnFrom(ArrayList<Assignment<Prop>> weightedSamples) {
        int i,nbSamples;
        double normalizer,aValue,Z;
        Prop aProposition;
        Assignment<Prop> anAssignment;
        nbSamples=weightedSamples.size();
        Z=(normalizer=0.);

        for(i=0;i<nbSamples;i++) {
            anAssignment=weightedSamples.get(i);
            aValue=anAssignment.value;
            if(aValue<=0) {
                System.err.println("Error :: "+this.getClass().getName() +
                                       ".learnFrom(ArrayList<Assignment<Prop>>)");
                System.err.println("Negative weight encountered");
                System.exit(0);
            }
            aProposition = anAssignment.attribute;
            if(theZero==null) theZero = aProposition.instanceNsize().zero();
            if(aProposition.compareTo(theZero)==0) Z+=aValue;
            else normalizer+=aValue;
        }
        clear();
        for(i=0;i<nbSamples;i++) {
            anAssignment=weightedSamples.get(i);
            aValue=anAssignment.value/normalizer;
            aProposition = anAssignment.attribute;
            if(aProposition.compareTo(theZero)!=0) add(aProposition,aValue);
        }
        return Z/(Z+normalizer); // return the percentage of reject
    }

    public Assignment<Prop> makeSample() {
        Assignment<Prop> assignment = new Assignment<Prop>();
        assignment.value=1.;
        assignment.attribute=mappedAttribute.get(drawTable_sampler()).attribute;
        return assignment;
    }

    public boolean setFuser(sB left, sB right,
                                RefereeFunctionDefault<Prop> theRefereeFunction) {
        ArrayList<sB> bbaTab= new ArrayList<sB>();
        bbaTab.add(left);
        bbaTab.add(right);
        return setFuser(bbaTab,theRefereeFunction);
    }

    public boolean setFuser(ArrayList<sB> bbaIn,
                            RefereeFunctionDefault<Prop> aRefereeFunction) {
        int i;
        theRefereeFunction=aRefereeFunction;
        fuserSources=bbaIn;
        BBASources.clear();
        for(i=0;i<fuserSources.size();i++) BBASources.add(fuserSources.get(i));

        for(i=0;i<bbaIn.size();i++) {
            if(!bbaIn.get(i).setTables_sampler()) return false;
        }
        answeredAttribute=new ArrayList<Assignment<Prop>>();
        for(i=0;i<bbaIn.size();i++) answeredAttribute.add(null);
        return true;
    }

    public Assignment<Prop> makeFusedSample() {
        Assignment<Prop> assignment = new Assignment<Prop>();
        Assignment<Prop> assignmentFromSource;
        ArrayList<Assignment<Prop>> fusedAssignment;
        int i;
        double cumul=0.;
        double x=rng.nextDouble();
        assignment.value=1.;
        for(i=0;i<fuserSources.size();i++) {
            sB aSource=fuserSources.get(i);
            assignmentFromSource=aSource.mappedAttribute.get(aSource.drawTable_sampler());
            answeredAttribute.set(i, assignmentFromSource);
        }
        fusedAssignment=theRefereeFunction.refereeFunction(answeredAttribute,BBASources);
        for(i=0;i<fusedAssignment.size();i++) {
            cumul+=fusedAssignment.get(i).value;
            if(cumul>x) {
                assignment.attribute=fusedAssignment.get(i).attribute;
                return assignment;
            }
        }
        return null;
    }

    public boolean setMixer(double[] weights, ArrayList<sB> bbaIn) {
        if(weights.length!=bbaIn.size()) return false;
        mixerSources=bbaIn;
        return setTables_mixer(weights);
    }

    public sB makeMixedChoice() {
        if(cumulativeDistribution_Mixer==null) return null;
        int choice = drawTable_mixer();
        return mixerSources.get(choice);
    }
}


