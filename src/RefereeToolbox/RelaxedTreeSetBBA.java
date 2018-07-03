/*
 *   RelaxedTreeSetBBA.java : part of package RefereeToolbox; Implementation of
 *          relaxed Basic Belief Assignment.
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

/**
 * Relaxed extension of {@link TreeSetBBA }.
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
public class RelaxedTreeSetBBA< Prop extends Lattice<Prop>,
                                         B extends RelaxedTreeSetBBA<Prop,B> >
                                            extends TreeSetBBA<Prop,B>
                                            implements RelaxedBBA<Prop,B> {

    @Override
    protected boolean relaxed() { return true; }


    //////////////////////////////////////////////////:
    // public part
    ///////////////

/**
 * Relax <i>this</i> so that it contains no more than <i>maxMem</i> assignments.
 *
 */
    @Override
    public boolean relax(int maxMem) { return super.relax(maxMem); }

/**
 * Relax <i>this</i> so that it contains no more than {@link RelaxedBBA#maxSize(int) }
 * assignments.
 *
 */
    @Override
    public boolean relax() { return super.relax(); }

/**
 * Set or return the default maximum number of assignments stored in <i>this</i>,
 * depending on parameter <i>newSize</i>.
 * If <i>newSize>0</i>, then set the default maximum number of assignments to <i>newSize</i>.
 * In any case, return the actual value of the default maximum number.
 * <BR><BR>
 * N.B. It is not possible to exceed this number of assignments, except for temporary
 * computed assignments.
 *
 */
    @Override
    public int maxSize(int newSize) { return super.maxSize(newSize); }
}
