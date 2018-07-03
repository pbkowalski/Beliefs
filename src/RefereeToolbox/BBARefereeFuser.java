/*
 *   BBARefereeFuser.java : part of package RefereeToolbox; Interface for fuser
 *           based on referee function.
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
 * Methods for fusing Basic Belief Assignments by the means of referee functions
 * and on the basis of an exact computation. This interface does not concern direct
 * rule implementations, {@link BBAFuser}, or implementations based on referee
 * sampling, {@link SampledBBARefereeFuser}.
 *
 * @see         BBAFuser
 * @see         SampledBBARefereeFuser
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
public interface BBARefereeFuser<Prop extends Lattice<Prop>, B extends BBARefereeFuser<Prop,B>>
                                        extends BasicBeliefAssignment<Prop,B> {

/**
 * Compute the combination (fusion) of basic belief assignments <i>left</i> and
 * <i>right</i> by means of the referee function <i>theRefereeFunction</i> and
 * store the result within <i>this</i>.
 *
 */
    B fuse(B left, B right, RefereeFunctionDefault<Prop> theRefereeFunction);

/**
 * Compute the combination (fusion) of basic belief assignments within array
 * <i>bbaIn</i> by means of the referee function <i>theRefereeFunction</i> and
 * store the result within <i>this</i>.
 *
 */
    B fuse(ArrayList<B> bbaIn, RefereeFunctionDefault<Prop> theRefereeFunction);

/**
 * Return the conflict of the last combination.
 *
 */
    double conflict();

}
