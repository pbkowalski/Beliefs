/*
 *   SampledBBA.java : part of package RefereeToolbox; Interface for sampled
 *          Basic Belief Assignment.
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
 * Methods for sampling Basic Belief Assignments and learning Basic Belief Assignments
 * from a samples vector.
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
public interface SampledBBA<Prop extends Lattice<Prop>, sB extends SampledBBA<Prop,sB> >
                                         extends BasicBeliefAssignment<Prop,sB> {

/**
 * Learn a new basic belief assignment for <i>this</i> from a given array of weighted
 * proposition particles. The weigthed particle are provided as an array of assignment
 * ArrayLattice<{@link Assignment }>.
 * A mesure of conflict is returned, as a percentage of the conflicting samples.
 *
 */
    double learnFrom(ArrayList<Assignment<Prop>> weightedSamples);

/**
 * Make a proposition sample according to the basic belief assignement stored
 * within <i>this</i>.
 * The produced sample is weighted (at this time, the weight is 1), and, for this
 * reason, is produced as an assignment {@link Assignment } with
 * value 1.
 *
 */
    Assignment<Prop> makeSample(); // weight may be null
                // in such a case, uniform weight is considered

 /**
 * Initialize the sampled mixer. Are needed as entry the basic belief assignments
 * to be mixed, provided within array <i>bbaIn</i>, and the weights stored within
 * <i>weights</i>. It is not necessary that the weights sum to 1, but the weights
 * cannot be negative.
 *
 */
    boolean setMixer(double[] weights, ArrayList<sB> bbaIn);

/**
 * Make a <i>random</i> choice among the basic belief assignments in proportion
 * to their respective weights.
 * Both the weights and basic belief assignments have been first provided to method
 * {@link SampledBBARefereeFuser#setMixer }. It is necessary to run the method
 * {@link SampledBBARefereeFuser#setMixer } before the first run of
 * {@link SampledBBARefereeFuser#makeMixedChoice() }.
 * The produced sample is weighted (at this time, the weight is 1), and, for this
 * reason, is produced as an assignment {@link Assignment } with
 * value 1.
 *
 */
    sB makeMixedChoice();

}
