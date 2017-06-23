/*
 *   ArrayBoolean.java : part of package RefereeToolbox; Implementation of
 *          Boolean algebra.
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
 *   along with RefereeToolbox.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************
 */

package RefereeToolbox;

/**
 * Implementation of a Boolean structure by means of an array of {@code long}.
 * The AND and OR operators are inherited from the operators {@code & } and {@code | }
 * working on {@code long}.
 * <i>zero</i> is defined by zeroing all bits. <i>one</i> is defined by setting to
 * 1 all active bits; the right bits of the {@code long } vector may be inactive,
 * depending on the size of the Lattice.
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
 public class ArrayBoolean<L extends ArrayBoolean<L>>
        extends ArrayLattice<L> implements ComplementedLattice<L> {


    //////////////////////////////////////////////////:
    // public part
    ///////////////

    /**
     * Compute the <b>complement</b> of <i>aProposition</i> and store the result within
     * <i>this</i>.
     * The complement/cocomplement operators are inherited from the operators {@code ~ }
     * working on {@code long}; exceeding bits are masked by a AND with <i>one</i>.
     * The complement and cocomplement are the same for {@link ArrayBoolean }
     * <BR><BR>
     * <b>Documentation inherited from {@link ComplementedLattice }:</b><BR>
     * {@inheritDoc}
     *
     */
    public L complement(L aProposition) {
        long[] rightmem=aProposition._memory;
        int i;
        for(i=0;i<=size_mem_1;i++)  _memory[i]=~rightmem[i];
        _memory[size_mem_1]&=highest_long_one;
        return (L) this;
    }

    /**
     * Compute the <b>complement</b> of <i>aProposition</i> and store the result within
     * <i>this</i>.
     * The complement/cocomplement operators are inherited from the operators {@code ~ }
     * working on {@code long}; exceeding bits are masked by a AND with <i>one</i>.
     * The complement and cocomplement are the same for {@link ArrayBoolean }
     * <BR><BR>
     * <b>Documentation inherited from {@link ComplementedLattice }:</b><BR>
     * {@inheritDoc}
     *
     */
    public L complement() {
        int i;
        for(i=0;i<=size_mem_1;i++)  _memory[i]=~_memory[i];
        _memory[size_mem_1]&=highest_long_one;
        return (L) this;
    }

    /**
     * Compute the <b>complement</b> of <i>aProposition</i> and store the result within
     * <i>this</i>.
     * The complement/cocomplement operators are inherited from the operators {@code ~ }
     * working on {@code long}; exceeding bits are masked by a AND with <i>one</i>.
     * The complement and cocomplement are the same for {@link ArrayBoolean }
     * <BR><BR>
     * <b>Documentation inherited from {@link ComplementedLattice }:</b><BR>
     * {@inheritDoc}
     *
     */
    public L cocomplement(L aProposition) {
        return complement(aProposition);
    }

    /**
     * Compute the <b>complement</b> of <i>aProposition</i> and store the result within
     * <i>this</i>.
     * The complement/cocomplement operators are inherited from the operators {@code ~ }
     * working on {@code long}; exceeding bits are masked by a AND with <i>one</i>.
     * The complement and cocomplement are the same for {@link ArrayBoolean }
     * <BR><BR>
     * <b>Documentation inherited from {@link ComplementedLattice }:</b><BR>
     * {@inheritDoc}
     *
     */
    public L cocomplement() {
        return complement();
    }

}
