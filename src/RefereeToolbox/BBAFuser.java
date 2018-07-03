/*
 *   BBAFuser.java : part of package RefereeToolbox; Interface for the fusion of
 *          Basic Belief Assigments.
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
 * Methods for fusing Basic Belief Assignments. This interface concerns direct
 * rule implementations. It does not concern referee-based fusers, for which there
 * are dedicated interfaces {@link BBARefereeFuser}, {@link SampledBBARefereeFuser}.
 *
 * @see         BBARefereeFuser
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
public interface BBAFuser<Prop extends Lattice<Prop>, B extends BBAFuser<Prop,B> >
                                        extends BasicBeliefAssignment<Prop,B> {

/**
 * Compute the combination (fusion) of basic belief assignments <i>left</i> and
 * <i>right</i> and store the result within <i>this</i>.
 *
 */
    B fuse(B left, B right);

/**
 * Compute the combination (fusion) of the basic belief assignments within array
 * <i>bbaIn</i> and store the result within <i>this</i>.
 *
 */
    B fuse(ArrayList<B> bbaIn);

/**
 * Return the conflict of the last combination.
 *
 */
    B conflict();

}
