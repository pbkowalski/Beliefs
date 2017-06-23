/*
 *   TreeSetBBA.java : part of package RefereeToolbox; Implementation of Basic
 *          Belief Assigment.
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
 * Implement the Basic Belief Assigment by means of a double {@link TreeSet }.
 * The TreeSet is applied to storing the list of assignments, typed {@link Assignment },
 * which define the Basic Belief Assigment. A double ordering of the assignment is
 * implemented: an increasing total order over the attributes of the assignments,
 * and a decreasing (total) ordering over the values of the assignments.
 * The attribute-related ordering is instrumental for logarithmic manipulation of
 * the assignments on the basis of an addressing by the attribute.
 * The value-related ordering is used by subclasses of {@link TreeSetBBA} for
 * relaxing the Basic Belief Assignment: by means of an interative summarization,
 * each step being logarithmic time, the size of the assignments list is maintained
 * under a maximal bound.
 * The relaxation is not available from this class.
 * <BR>
 * These logarithmic operations are made possible by the property of the class
 * {@link TreeSet }.
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
public class TreeSetBBA<Prop extends Lattice<Prop>,
                                            B extends TreeSetBBA<Prop,B> >
                                             extends BBACommon<Prop,B> {

    protected int theMaxSize = 20;

    protected boolean relaxed() { return false; }

    protected final Comparator< Assignment<Prop> > comparatorA =
            new Comparator< Assignment<Prop> > () {
        //
        public int compare(Assignment<Prop> left, Assignment<Prop> right) {

            return left.attribute.compareTo(right.attribute);
        }
    };


    protected final Comparator< Assignment<Prop> > comparatorVA =
            new Comparator< Assignment<Prop> > () {

        public int compare(Assignment<Prop> left, Assignment<Prop> right) {
            // Comparison order is reverted
            int answerV =  right.value.compareTo(left.value);
            if(answerV!=0) return answerV;
            return left.attribute.compareTo(right.attribute);
        }
    };


    protected TreeSet<Assignment<Prop>> bbaTreeA = new TreeSet<Assignment<Prop>>(comparatorA);

    protected TreeSet<Assignment<Prop>> bbaTreeVA = new TreeSet<Assignment<Prop>>(comparatorVA);


    protected boolean _relaxStep() { // only used with the relaxed extensions of the class
        if(bbaTreeA.size()<2) {
            System.err.println("Error :: "+this.getClass().getName() +
                "._relaxStep() :: bbaTreeA.size()<2");
            System.exit(0);
        }
        Assignment<Prop> lastPair=bbaTreeVA.pollLast();
        Assignment<Prop> prevLastPair = bbaTreeVA.pollLast();
        bbaTreeA.remove(lastPair);
        bbaTreeA.remove(prevLastPair);
        prevLastPair.value+=lastPair.value;
        prevLastPair.attribute.or(prevLastPair.attribute,lastPair.attribute);
        add(prevLastPair);
        return true;
    }


    //////////////////////////////////////////////////:
    // public part
    ///////////////

/**
 * Do an exact copy of input into <i>this</i>.
 *
 */
    @Override
    public B duplicate(B input) {
        load(input.bbaTreeVA);
        return (B) this;
    }
    
/**
 * Return a representation of the state of <i>this</i> printed as
 * a String. Typically, this state is the list of all stored assignments (proposition
 * and value). Depending on the parameter <i>choix</i> in entry, the representation
 * is modified as folllows:<br><br>
 * <i>choix==0</i>  --  Nothing is printed.
 * <br>
 *<i>choix==1</i>  --  Assignments are printed in increasing order of their
 * propositions,
 * <br>
 *<i>choix==2</i>  --  Assignments are printed in decreasing order of their
 * value,
 * <br>
 *<i>choix==3</i>  --  Do both print in that order.
 * <br><br>
 * Assignments are printed according to the following format:
 * <br>
 * <code> attribute -> value </code>
 * <br>
 * The attribute is printed according to its own state method {@link Lattice#state() }.
 *
 */
    @Override
    public String state(int choix) {
        String theState="";
        if((choix&1)==1) {
            theState="bbaTreeA:\n";
            for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                                        it.hasNext(); ) {
                Assignment<Prop> currentPair=it.next();
                theState+=currentPair.attribute.state() + " -> " +
                                                currentPair.value + "\n";
            }
        }
        if((choix&3)==3) theState+="\n";
        if((choix&2)==2) {
            theState+="bbaTreeVA:\n";
            for (Iterator<Assignment<Prop>> it = bbaTreeVA.iterator();
                                                        it.hasNext(); ) {
                Assignment<Prop> currentPair=it.next();
                theState+=currentPair.attribute.state() + " -> " +
                                                currentPair.value + "\n";
            }
        }
        return theState;
    }

    
/**
 * Create an array of the assignments stored within the class. These assignments not
 * necessary sum to 1. Notice that the assignments should be positively valued
 * (focal elements), although <i>this</i> is not a strict requirement.
 *
 */
    @Override
    public ArrayList<Assignment<Prop>> toArray() {
        return new ArrayList<Assignment<Prop>> (bbaTreeVA);
    }

/**
 * Remove all assignments from <i>this</i>. The class instance is cleared.
 *
 */
    @Override
    public void clear() {
        bbaTreeA.clear();
        bbaTreeVA.clear();
        update_notification();
    }

/**
 * Add an assignment characterized by a proposition <i>aProposition</i> and a value
 * <i>anAssignmentValue</i> to <i>this</i>.
 * If an assignment already exists for <i>aProposition</i>, say with value <i>theOldValue</i>,
 * then the new assignement of <i>aProposition</i> is stored with value
 * <i>theOldValue+anAssignmentValue</i>.
 *
 */
    @Override
    public boolean add(Prop aProposition, double anAssignmentValue) {
        if(anAssignmentValue<=0) return false;
        Assignment<Prop> anAssignment=new Assignment<Prop>();
        anAssignment.attribute=aProposition.clone(); // avoid border effect with entry
        anAssignment.value=anAssignmentValue;
        Assignment<Prop>  foundPair=findProposition(anAssignment);
        if(foundPair!=null) {
                anAssignment.value+= foundPair.value;
                bbaTreeA.remove(foundPair);
                bbaTreeVA.remove(foundPair);
        }
        bbaTreeA.add(anAssignment);
        bbaTreeVA.add(anAssignment);
        update_notification();
        return true;
    }

/**
 * Search if there is an assignment stored within the class, and which attribute
 * is equal to the attribute of <i>anAssignment</i>.
 * Return the found assignment, if there is one. Othewise, return <i>null</i>.
 *
 */
    @Override
    public Assignment<Prop> findProposition(Assignment<Prop> anAssignment) {
        Assignment<Prop>  foundPair=bbaTreeA.ceiling(anAssignment);
        if(foundPair!=null) {
            if(foundPair.attribute.compareTo(anAssignment.attribute)==0) {
                return foundPair;
            }
        }
        return null;
    }
    

/**
 * Compute the distance between <i>this</i> and input <i>aBba</i>. 
 * The method described in A. Jousselme et al. 2001 paper "
 * A new distance between two bodies of evidence" is used in this function.
 */
    public double findDistance(TreeSetBBA<Prop,B> aBba){
    	double distance=0;
    //	Iterator<Assignment<Prop>> it = this.bbaTreeA.iterator();
    //	int temp = this.bbaTreeA.last().attribute.size(0);
 //   	finalPowerset temp2 = new finalPowerset();
 //   	temp2.z

    	Prop temp = this.bbaTreeA.last().attribute.instanceNsize();
    	double Dij;
    	int Dij_nom, Dij_denom;
    	temp.zero();
    	int iterations=0; //lazy method of doing it, but this is temporary code
    	double Bba1Norm=0,Bba2Norm=0,Bba1bba2 = 0;
    	double test1=0, test2=0,test3=0;
        for (Iterator<Assignment<Prop>> it = this.bbaTreeA.iterator();
                it.hasNext(); ) {
        	Assignment<Prop> Bba1Current=it.next(); //m1(ai)
        	for (Iterator<Assignment<Prop>> bba1norm_it = this.bbaTreeA.iterator();
        			bba1norm_it.hasNext(); ) {
        			Assignment<Prop> Bba1It=bba1norm_it.next(); //m1(aj)
            		temp.and(Bba1Current.attribute, Bba1It.attribute); //(Ai AND Aj)
            		Dij_nom=temp.cardinal();
            		temp.or(Bba1Current.attribute, Bba1It.attribute); //(Ai OR Aj)
            		Dij_denom=temp.cardinal();
            		if (Dij_nom == Dij_denom){
            			Dij=1; // avoids the problem with division by zero (J(0,0)=1) and may reduce comp load due to avoidance of f.p. arithmetic
            		}
            		else{
            		Dij = (double)Dij_nom / (double)Dij_denom;}
            		Bba1Norm += Bba1Current.value * Bba1It.value * Dij;
        	}
    		
        	for (Iterator<Assignment<Prop>> it2 = aBba.bbaTreeA.iterator(); 
        			it2.hasNext();){
            		Assignment<Prop> Bba2Current=it2.next(); //m2(aj)
            		temp.and(Bba1Current.attribute, Bba2Current.attribute); //(Ai AND Aj)
            		Dij_nom=temp.cardinal();
            		temp.or(Bba1Current.attribute, Bba2Current.attribute); //(Ai OR Aj)
            		Dij_denom=temp.cardinal();
            		Dij = (double)Dij_nom / (double)Dij_denom;
            		Bba1bba2 += Bba1Current.value * Bba2Current.value * Dij;
	            		if (iterations ==0){
	                    	for (Iterator<Assignment<Prop>> bba2norm_it = aBba.bbaTreeA.iterator();
	                    			bba2norm_it.hasNext(); ) {
	                    			Assignment<Prop> Bba2It=bba2norm_it.next(); //m1(aj)
	                        		temp.and(Bba2Current.attribute, Bba2It.attribute); //(Ai AND Aj)
	                        		Dij_nom=temp.cardinal();
	                        		temp.or(Bba2Current.attribute, Bba2It.attribute); //(Ai OR Aj)
	                        		Dij_denom=temp.cardinal();
	                        		if (Dij_nom == Dij_denom){
	                        			Dij=1; // avoids the problem with division by zero (J(0,0)=1) and may reduce comp load due to avoidance of f.p. arithmetic
	                        		}
	                        		else{
	                        		Dij = (double)Dij_nom / (double)Dij_denom;}
	                        		Bba2Norm += Bba2Current.value * Bba2It.value * Dij;
	                    	}
	            		}
        			}
        	iterations++;
        }
        distance = 0.5*(Bba1Norm + Bba2Norm - 2*Bba1bba2);
        distance = Math.sqrt(distance);
    	return distance;
    }
    
    public double commonalityDivergence (TreeSetBBA<Prop,B> aBba){
    	double comdiv=0;
    	for (Iterator<Assignment<Prop>> it = this.bbaTreeA.iterator();
                it.hasNext(); ) { //iterate on this
        	Assignment<Prop> Bba1Current=it.next();
    	}
    	return comdiv;
    }
    /**
     * Get the dissonance value of the mass assignment function stored in <i>this</i>
     *
     */
        public double Dissonance() {
            double dissonance=0.;
            for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                                it.hasNext(); ) {
                Assignment<Prop> currentPair=it.next();
                double Plaus=this.Pl(currentPair.attribute);
                double log2=Math.log(Plaus)/Math.log(2.0);
                dissonance -= currentPair.value * log2;
            }
            return dissonance;
        }
        /**
         * Get the information content of evidence as defined by Smets
         * 
         */
        public double Infbel() {
            double infbel=0.;
            for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                                it.hasNext(); ) {
                Assignment<Prop> currentPair=it.next();
                double Comm=this.Comm(currentPair.attribute);
                double log2=Math.log(Comm)/Math.log(2.0);
                infbel -= currentPair.value * log2;
            }
            return infbel;
        }

        /**
         * Get the confusion value of <i>this</i> 
         */
        public double Confusion() {
            double confusion=0.;
            for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                                it.hasNext(); ) {
                Assignment<Prop> currentPair=it.next();
                double Belief=this.Bel(currentPair.attribute);
                double log2=Math.log(Belief)/Math.log(2.0);
                confusion -= currentPair.value * log2;
            }
            return confusion;
        }
        public double Discord() {
            double discord=0.;
            for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                                it.hasNext(); ) {
                Assignment<Prop> currentPair=it.next();
                double pbet=this.Pbet(currentPair.attribute);
                double log2=Math.log(pbet)/Math.log(2.0);
                discord -= currentPair.value * log2;
            }
            return discord;
        }                
/**
 * Get the belief of <i>aProposition</i>. Although not required, it is assumed that
 * the assignments sum to 1.
 *
 */
    @Override
    public double Bel(Prop aProposition) {
        double belief=0.;
        for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                            it.hasNext(); ) {
            Assignment<Prop> currentPair=it.next();
            if(aProposition.contains(currentPair.attribute))
                                            belief+=currentPair.value;
        }
        return belief;
    }
    /**
     * Get the pignistic probability of <i>aProposition</i>. Although not required, it is assumed that
     * the assignments sum to 1.
     *
     */
    public double Pbet(Prop aProposition) {
        double prob=0.;
        for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                            it.hasNext(); ) {
            Assignment<Prop> currentPair=it.next();
            int size = currentPair.attribute.cardinal(); //debug purposes only
            if(currentPair.attribute.contains(aProposition))
                                            prob+=currentPair.value / (double)size;
        }
        return prob;
    }
    
    /**
     * Get the commonality of <i>aProposition</i>. Although not required, it is assumed that
     * the assignments sum to 1.
     *
     */
    public double Comm(Prop aProposition) {
        double com=0.;
        for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                            it.hasNext(); ) {
            Assignment<Prop> currentPair=it.next();
            if(currentPair.attribute.contains(aProposition))
                                            com+=currentPair.value;
        }
        return com;
    }
/**
 * Get the plausibility of <i>aProposition</i>. Although not required, it is assumed that
 * the assignments sum to 1.
 *
 */
    @Override
    public double Pl(Prop aProposition) {
        double belief=0.;
        for (Iterator<Assignment<Prop>> it = bbaTreeA.iterator();
                                            it.hasNext(); ) {
            Assignment<Prop> currentPair=it.next();
            if(aProposition.intersects(currentPair.attribute))
                                            belief+=currentPair.value;
        }
        return belief;
    }

/**
 * Remove the assignment related to proposition <i>aProposition</i>, if there is
 * such assignment stored within <i>this</i>.
 * Return the value assigned to the proposition.
 *
 */
    @Override
    public double remove(Prop aProposition) {
        Assignment<Prop>  foundPair=findProposition(aProposition);
        if(foundPair!=null) {
                double theValue=foundPair.value;
                bbaTreeA.remove(foundPair);
                bbaTreeVA.remove(foundPair);
                update_notification();
                return theValue;
            }
        return 0.;
    }

/**
 * Make a mix of the basic belief assignments stored within the entry, <i>bbaIn</i>
 * according to their respective weight <i>weight</i>. This mix is stored into <i>this</i>.
 *
 */
    @Override
    public B mix(ArrayList<B> bbaIn, double[] weight) {
        int nbIn=bbaIn.size();

        if(nbIn<1) return null;
        if(nbIn!=weight.length) return null;
        double cumul=0;
        double aValue;
        int i;
        Iterator j;
        Assignment<Prop> refOut;
        for(i=0;i<nbIn;i++) {
            aValue=weight[i];
            if(aValue<0) return null;
            cumul+=aValue;
        }
        if(cumul==0) return null;
        clear();
        B bbaResult=instance();
        for(i=0;i<nbIn;i++) {
            aValue=weight[i]/cumul;
            for(j=(bbaIn.get(i)).bbaTreeVA.iterator(); j.hasNext();) {
                refOut=(Assignment<Prop>) j.next();
                bbaResult.add(refOut.attribute, aValue*refOut.value);
            }
        }
        duplicate(bbaResult);
        update_notification();
        return (B) this;
    }

    @Override
    public void update_notification() { // called each time a change is done
        if(relaxed()) relax();
        else super.update_notification();
    }


    //////////////////////////////////////////////////:
    // reserved part
    ///////////////

/**
 * This method is not accessible from class {@link TreeSetBBA } and generates an error
 * report and a code exit.
 *
 */
    public boolean relax(int maxMem) {
        if(!relaxed()) {
            System.err.println("Class" + this.getClass().getName()
                                    + " does not implement relaxation");
            System.exit(0);
        }
        if(maxMem<1) return false;
        boolean test=true;
        while(bbaTreeA.size()>maxMem) {
            test=test&&_relaxStep();
        }
        super.update_notification();
        return test;
    }

/**
 * This method is not accessible from class {@link TreeSetBBA } and generates an error
 * report and a code exit.
 *
 */
    public boolean relax(){
        if(!relaxed()) {
            System.err.println("Class" + this.getClass().getName()
                                    + " does not implement relaxation");
            System.exit(0);
        }
        return relax(maxSize(-1));
    }

/**
 * This method is not accessible from class {@link TreeSetBBA } and generates an error
 * report and a code exit.
 *
 */
    public int maxSize(int newSize) {
        if(!relaxed()) {
            System.err.println("Class" + this.getClass().getName()
                                    + " does not implement relaxation");
            System.exit(0);
        }
        if(newSize>0) {
            theMaxSize=newSize;
            relax();
        }
        return theMaxSize;
    }

}
