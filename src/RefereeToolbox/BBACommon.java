/*
 *   BBACommon.java : part of package RefereeToolbox; Basical implementation of
 *          the Basic Belief Assigment.
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
 * Basical implementation of the Basic Belief Assigment structure.
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
public class BBACommon <Prop extends Lattice<Prop>,
                                            B extends BBACommon<Prop,B> >
                                            implements BasicBeliefAssignment<Prop,B> {


    //////////////////////////////////////////////////:
    // public part
    ///////////////

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public B duplicate(B input) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".duplicate(B) is not implemented!");
        System.err.println("Please implement clone() in your class with @Override");
        System.exit(0);
        return null;
    }

    @Override
    public B clone() {
        return instance().duplicate((B) this);
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public B instance() {
        System.err.println("Error :: "+this.getClass().getName() +
                ".clone() is not implemented!");
        System.err.println("Please implement clone() in your class with @Override");
        System.exit(0);
        return null;
    }

    /**
     * This method is not defined for class {@link BBACommon } and produces the String
     * {@code "UNDEFINED" }.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public String state(int choix) {
        return "UNDEFINED";
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public ArrayList<Assignment<Prop>> toArray() {
        System.err.println("Error :: "+this.getClass().getName() +
                ".toArray() is not implemented!");
        System.err.println("Please implement toArray() in your class with @Override");
        System.exit(0);
        return null;
    }

    public boolean load(Collection<Assignment<Prop>> anAssignmentTab) {
        clear();
        addAll(anAssignmentTab);
        return true;
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public void clear() {
        System.err.println("Error :: "+this.getClass().getName() +
                ".clear() is not implemented!");
        System.err.println("Please implement clear() in your class with @Override");
        System.exit(0);
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public boolean add(Prop aProposition, double anAssignmentValue) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".add() is not implemented!");
        System.err.println("Please implement add() in your class with @Override");
        System.exit(0);
        return true;
    }

    public boolean add(Assignment<Prop> anAssignment) {
        return add(anAssignment.attribute,anAssignment.value);
    }

    public boolean addAll(Collection<Assignment<Prop>> assignTab) {
        //bbaTree;
        boolean test=true;
        for (Iterator<Assignment<Prop>> it = assignTab.iterator();
                                            it.hasNext(); ) {
            test=test&&add(it.next());
       }
        return test;
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public Assignment<Prop> findProposition(Assignment<Prop> anAssignment) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".findProposition(Assignment<Prop>) is not implemented!");
        System.err.println("Please implement findProposition(Assignment<Prop>)" +
                " in your class with @Override");
        System.exit(0);
        return null;
    }

    public Assignment<Prop> findProposition(Prop aProposition) {
        Assignment<Prop> searchAssign = new Assignment<Prop>();
        searchAssign.attribute=aProposition;
        return findProposition(searchAssign);
    }

    public double m(Prop aProposition) {

        Assignment<Prop>  foundPair=findProposition(aProposition);
        if(foundPair!=null) {
                return foundPair.value;
            }
        return 0.;
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public double Bel(Prop aProposition) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".Bel(Prop) is not implemented!");
        System.err.println("Please implement Bel(Prop) in your class with @Override");
        System.exit(0);
        return -1;
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public double Pl(Prop aProposition) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".Pl(Prop) is not implemented!");
        System.err.println("Please implement Pl(Prop) in your class with @Override");
        System.exit(0);
        return -1;
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public double remove(Prop aProposition) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".remove(Prop) is not implemented!");
        System.err.println("Please implement remove(Prop) in your class with @Override");
        System.exit(0);
        return -1.;
    }

    /**
     * This method is not defined for class {@link BBACommon } and generates an error
     * report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link BasicBeliefAssignment }:</b><BR>
     * {@inheritDoc}
     */
    public B mix(ArrayList<B> bbaIn, double[] weight) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".mix(ArrayList<B>, double[]) is not implemented!");
        System.err.println("Please implement mix(ArrayList<B>, double[]) in your" +
                " class with @Override");
        System.exit(0);
        return null;
    }

    public void update_notification() { // called each time a change is done
    }

}
