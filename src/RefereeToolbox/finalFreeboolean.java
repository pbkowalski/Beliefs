/*
 *   finalFreeboolean.java : part of package RefereeToolbox; Implementation of
 *          free Boolean algebra.
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
 * Final implementation of class {@link Freeboolean }. This class is not generic, and
 * thus, allows and needs instantiation. The method {@link finalFreeboolean#instance() }
 * is implemented.
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
public final class finalFreeboolean extends Freeboolean<finalFreeboolean>  {


    //////////////////////////////////////////////////:
    // public part
    ///////////////

/**
 * Create and return an instance of <i>this</i> (i.e. work like a <b>new</b> on
 * the Class of <i>this</i>).
 * N.B. {@link Lattice#instance() } cannot be defined
 * for generic classes but <u>is necessary</u> for some methods. <u>It has to be
 * defined with the non generic (typically final) sub-classes</u>.
 * <br><br>
 * For a given non generic sub-class <i>myNonGenericSubclass</i>, a typical
 * definition of {@link Lattice#instance() } is as follows:
 * <br><br>
 * <font color="#004488"><code>
 * {@code @Override}
 * <br>
 * public myNonGenericSubclass instance() { return new myNonGenericSubclass(); }
 * </code></font>
 *
 */
    @Override
    public finalFreeboolean instance() { return new finalFreeboolean(); }
}
