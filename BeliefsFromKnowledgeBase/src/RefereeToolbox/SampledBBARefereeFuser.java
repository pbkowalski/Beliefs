/*
 *   SampledBBARefereeFuser.java : part of package RefereeToolbox; Interface for
 *            sampled fuser based on referee function.
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
 * and on the basis of a particle approximation. This interface does not concern
 * direct rule implementations, {@link BBAFuser}, or exact referee-based fusion
 * {@link BBARefereeFuser}.
 *
 * @see         BBAFuser
 * @see         BBARefereeFuser
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
public interface SampledBBARefereeFuser<Prop extends Lattice<Prop>,
                        sB extends SampledBBARefereeFuser<Prop,sB> >
                    extends SampledBBA<Prop,sB> {

/**
 * Initialize the sampled fuser. Are needed as entry the basic belief assignments
 * to be fused, provided within array <i>bbaIn</i>, and a referee function
 * <i>theRefereeFunction</i>.
 *
 */
    boolean setFuser(ArrayList<sB> bbaIn, RefereeFunctionDefault<Prop> theRefereeFunction);

/**
 * Initialize the sampled fuser. Are needed as entry the basic belief assignments
 * to be fused, provided as <i>left</i> and <i>right</i>, and a referee function
 * <i>theRefereeFunction</i>.
 *
 */
    boolean setFuser(sB left, sB right, RefereeFunctionDefault<Prop> theRefereeFunction);

/**
 * Make a proposition sample according to the fused basic belief assignement implied
 * by the basic belief assignements and referee function provided to method
 * {@link SampledBBARefereeFuser#setFuser(java.util.ArrayList,
 * RefereeToolbox.RefereeFunctionDefault) }
 * or {@link SampledBBARefereeFuser#setFuser(RefereeToolbox.SampledBBARefereeFuser,
 * RefereeToolbox.SampledBBARefereeFuser, RefereeToolbox.RefereeFunctionDefault)  }.
 * It is necessary to run these methods
 * before the first run of
 * {@link SampledBBARefereeFuser#makeFusedSample() }.
 * The produced sample is weighted (at this time, the weight is 1), and, for this
 * reason, is produced as an assignment {@link Assignment } with
 * value 1.
 *
 */
    Assignment<Prop> makeFusedSample();

}

