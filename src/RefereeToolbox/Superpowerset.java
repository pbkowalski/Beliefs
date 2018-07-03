/*
 *   Superpowerset.java : part of package RefereeToolbox; Implementation of
 *          superpowerset.
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
 * Implementation of a Superpowerset structure by means of an array of {@code long}.
 * Structural and logical  methods are inherited from {@link Freeboolean }.
 * However, Superpowersets come with the constraint that the union of all atomic
 * propositions is <i>one</i> or equivalently, the intersection of all negated atomic
 * propositions is zero:
 * <BR><BR>
 * <i> <b>OR</b><sub>0 =< i < sizeFrame</sub> atomic(i) = one</i>
 * <BR>
 * <i> <b>AND</b><sub>0 =< i < sizeFrame</sub> atomic(i) = zero</i>
 * <BR><BR>
 * Then, <i>zero</i> is still defined by zeroing all bits; but <i>one</i> is defined
 * by setting to 1 all active bits except the first one.
 * The complement/cocomplement operators are inherited from the operators {@code ~ }
 *  working on {@code long}; exceeding bits are masked by a AND with <i>one</i>.
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
public class Superpowerset<L extends Superpowerset<L> > extends Freeboolean<L> {


    //////////////////////////////////////////////////:
    // public part
    ///////////////

/**
 * Set <i>this</i> to one, the neutral element for
 * {@link Superpowerset#and(java.lang.Object, java.lang.Object) }.
 * For the class {@link Superpowerset }, the first bit of <i>one</i> is set to 0.
 *
 */
    @Override
    public L one() {
        super.one();
        _memory[0]&=0xFFFFFFFFFFFFFFFEL; // remove the first bit
        return (L) this;
    }

/**
 * Compute the <b>complement</b> of <i>aProposition</i> and store the result within
 * <i>this</i>.
 * For the class {@link Superpowerset }, the first bit of the result is set to 0.
 * <BR><BR>
 * <b>Documentation inherited from {@link Freeboolean }:</b><BR>
 * {@inheritDoc}
 *
 */
    @Override
    public L complement(L aProposition) {
        super.complement(aProposition);
        _memory[0]&=0xFFFFFFFFFFFFFFFEL; // remove the first bit
        return (L) this;
    }

/**
 * Compute the <b>complement</b> of <i>this</i> and store the result within
 * <i>this</i>.
 * For the class {@link Superpowerset }, the first bit of the result is set to 0.
 * <BR><BR>
 * <b>Documentation inherited from {@link Freeboolean }:</b><BR>
 * {@inheritDoc}
 *
 */
    @Override
    public L complement() {
        super.complement();
        _memory[0]&=0xFFFFFFFFFFFFFFFEL; // remove the first bit
        return (L) this;
    }

}
