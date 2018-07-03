/*
 *   RefereeFunctionDisjunctive.java : part of package RefereeToolbox;
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
 * This class implements the Referee Function encoding the disjunctive combination
 * rule.
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
public class RefereeFunctionDisjunctive<Prop extends Lattice<Prop> >
                        extends RefereeFunctionDefault<Prop> {


    //////////////////////////////////////////////////:
    // public part
    ///////////////

    @Override
    public ArrayList<Assignment<Prop>> refereeFunction(ArrayList<Assignment<Prop>> assignIn,
                                                    ArrayList<minAssignment<Prop>>  bbaIn) {
        if(assignIn==null) {
            System.err.println("Error :: "+this.getClass().getName() +
                ".refereeFunction(ArrayList<Assignment<Prop>>,ArrayList<B>)");
            System.err.println("Error :: assignIn is null");
            System.exit(0);
        }
        if(assignIn.size()<1) {
            System.err.println("Error :: "+this.getClass().getName() +
                ".refereeFunction(ArrayList<Assignment<Prop>>,ArrayList<B>)");
            System.err.println("Error :: assignIn is empty");
            System.exit(0);
        }

        int i;
        ArrayList<Assignment<Prop>> arbitrament=new ArrayList<Assignment<Prop>>();
        Assignment<Prop> finalAssignment = new Assignment<Prop>();
        Prop fusedProp=assignIn.get(0).attribute.clone();
        finalAssignment.attribute=fusedProp;
        finalAssignment.value=1.;
        arbitrament.add(finalAssignment);

        for(i=1;i<assignIn.size();i++) {
            fusedProp.or(fusedProp,assignIn.get(i).attribute);
        }
        return arbitrament;
    }

}
