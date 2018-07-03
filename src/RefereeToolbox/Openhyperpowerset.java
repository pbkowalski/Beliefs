/*
 *   Openhyperpowerset.java : part of package RefereeToolbox; Implementation
 *          of open hyperpowerset.
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
 * Implementation of an open hyperpowerset structure. An open hyperpowerset is an
 * hyperpowerset defined on the basis of a free boolean algebra: it is generated
 * from the atomic propositions of the free boolean algebra but without the use of
 * the complement operator of the free boolean algebra.
 * <BR><BR>
 * The open hyperpowerset implies an open world hypothesis, that is:
 *<BR><BR>
 * <i> <b>OR</b><sub>0 =< i < sizeFrame</sub> atomic(i)  !=  one</i>
 * <BR><BR>
 * Although generated without the complement of the free Boolean algebra, the open
 * hyperpowerset actually has proper complement and cocomplement operators.
 * The complement and cocomplement are distinct operators for the open hyperpowerset.
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
public class Openhyperpowerset<L extends Openhyperpowerset<L>> extends Freeboolean<L> {
    protected L theZero = null;
    protected L theOne = null;

    /**
     * Compute the <b>complement</b> of <i>aProposition</i> and store the result
     * within <i>this</i>.
     * The complement operator for the open hyperpowerset is defined by:
     * <BR><BR>
     * <i> complement(zero) = one</i>
     * <BR>
     * <i> complement(X) = zero</i> if <i>X != zero</i>
     *
     */
    @Override
    public L complement(L aProposition) {
        if(theZero==null) {
            theZero = instanceNsize().zero();
            theOne = instanceNsize().one();
        }
        if(aProposition.compareTo(theZero)==0) one();
        else zero();
        return (L) this;
    }

    /**
     * Compute the <b>complement</b> of <i>this</i> and store the result within
     * <i>this</i>.
     * The complement operator for the open hyperpowerset is defined by:
     * <BR><BR>
     * <i> complement(zero) = one</i>
     * <BR>
     * <i> complement(X) = zero</i> if <i>X != zero</i>
     *
     */
    @Override
    public L complement() {
        if(theZero==null) {
            theZero = instanceNsize().zero();
            theOne = instanceNsize().one();
        }
        if(compareTo(theZero)==0) one();
        else zero();
        return (L) this;
    }

    /**
     * Compute the <b>cocomplement</b> of <i>aProposition</i> and store the result
     * within <i>this</i>.
     * The cocomplement operator for the open hyperpowerset is defined by:
     * <BR><BR>
     * <i> cocomplement(one) = zero</i>
     * <BR>
     * <i> cocomplement(X) = one</i> if <i>X != one</i>
     *
     */
    @Override
    public L cocomplement(L aProposition) {
        if(theZero==null) {
            theZero = instanceNsize().zero();
            theOne = instanceNsize().one();
        }
        if(aProposition.compareTo(theOne)==0) zero();
        else one();
        return (L) this;
    }

    /**
     * Compute the <b>cocomplement</b> of <i>this</i> and store the result
     * within <i>this</i>.
     * The cocomplement operator for the open hyperpowerset is defined by:
     * <BR><BR>
     * <i> cocomplement(one) = zero</i>
     * <BR>
     * <i> cocomplement(X) = one</i> if <i>X != one</i>
     *
     */
    @Override
    public L cocomplement() {
        if(theZero==null) {
            theZero = instanceNsize().zero();
            theOne = instanceNsize().one();
        }
        if(compareTo(theOne)==0) zero();
        else one();
        return (L) this;
    }

}
