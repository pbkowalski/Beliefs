/*
 *   ComplementedLattice.java : part of package RefereeToolbox; Interface
 *          for complemented Lattice.
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
 * Contains the methods for a class implementing a Lattice structure with a
 * complement or a pseudo-complement operator.
 * Boolean algebra, powerset, as well as Heyting algebra or closed/open Hyperpowersets
 * are examples of complemented lattices.
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
public interface ComplementedLattice<L> extends Lattice<L> {

/**
 * Compute the <b>complement</b> of <i>aProposition</i> and store the result within
 * <i>this</i>.
 * <BR><BR>
 * <b>Definition.</b> The complement of a proposition <i>X</i> of a lattice <i>L</i>
 * is: <i> comp(X) = <b>OR</b><sub>Y : Y AND X = zero</sub> Y </i>
 * <BR><BR>
 * <b>Theorem 1.</b> If the lattice is a boolean algebra, then complement and cocomplement
 * are identical, <i>comp(X) =cocomp(X)</i>
 * <BR>
 * <b>Theorem 2.</b> <i>cocomp(X)</i> contains <i>comp(X)</i>
 *
 * @see ComplementedLattice#cocomplement(java.lang.Object)
 */
    L complement(L aProposition);

/**
 * Compute the <b>complement</b> of <i>this</i> and store the result within
 * <i>this</i>.
 * <BR><BR>
 * <b>Definition.</b> The complement of a proposition <i>X</i> of a lattice <i>L</i>
 * is: <i> comp(X) = <b>OR</b><sub>Y : Y AND X = zero</sub> Y </i>
 * <BR><BR>
 * <b>Theorem 1.</b> If the lattice is a boolean algebra, then complement and cocomplement
 * are identical, <i>comp(X) =cocomp(X)</i>
 * <BR>
 * <b>Theorem 2.</b> <i>cocomp(X)</i> contains <i>comp(X)</i>
 *
 * @see ComplementedLattice#cocomplement()
 */
    L complement();

/**
 * Compute the <b>cocomplement</b> of <i>aProposition</i> and store the result
 * within <i>this</i>.
 *
 * <BR><BR>
 * <b>Definition.</b> The cocomplement of a proposition <i>X</i> of a lattice <i>L</i>
 * is: <i> cocomp(X) = <b>AND</b><sub>Z : Z OR X = one</sub> Z </i>
 * <BR><BR>
 * <b>Theorem 1.</b> If the lattice is a boolean algebra, then complement and cocomplement
 * are identical, <i>comp(X) =cocomp(X)</i>
 * <BR>
 * <b>Theorem 2.</b> <i>cocomp(X)</i> contains <i>comp(X)</i>
 *
 * @see ComplementedLattice#complement(java.lang.Object)
 */
    L cocomplement(L aProposition);

/**
 * Compute the <b>cocomplement</b> of <i>this</i> and store the result
 * within <i>this</i>.
 *
 * <BR><BR>
 * <b>Definition.</b> The cocomplement of a proposition <i>X</i> of a lattice <i>L</i>
 * is: <i> cocomp(X) = <b>AND</b><sub>Z : Z OR X = one</sub> Z </i>
 * <BR><BR>
 * <b>Theorem 1.</b> If the lattice is a boolean algebra, then complement and cocomplement
 * are identical, <i>comp(X) =cocomp(X)</i>
 * <BR>
 * <b>Theorem 2.</b> <i>cocomp(X)</i> contains <i>comp(X)</i>
 *
 * @see ComplementedLattice#complement()
 */
    L cocomplement();
}
