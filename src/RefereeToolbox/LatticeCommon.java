/*
 *   LatticeCommon.java : part of package RefereeToolbox; Basical implementation
 *          of lattice.
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
 * Basical implementation of the Latice structure
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
public class LatticeCommon<L extends LatticeCommon<L>>
        implements Lattice<L>  {

    //////////////////////////////////////////////////:
    // public part
    ///////////////

    /**
     * This method is not defined for class {@link LatticeCommon } and produces
     * the String {@code "UNDEFINED" }.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public String state() {
        return "UNDEFINED";
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public L zero() {
        System.err.println("Error :: "+this.getClass().getName() +
                ".zero() is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return null;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public L one() {
        System.err.println("Error :: "+this.getClass().getName() +
                ".one() is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return null;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public L and(L left, L right) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".and(L,L) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return null;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public L or(L left, L right) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".or(L,L) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return null;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public  boolean intersects(L aProposition) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".intersects(L) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return false;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public boolean contains(L aProposition) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".contains(L) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return true;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public int compareTo(L aProposition) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".compareTo(L) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return 0;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public L size(L input) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".size(L) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return null;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public int size(int newSize) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".size(int) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return -1;
    }

    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public L duplicate(L input) {
        System.err.println("Error :: "+this.getClass().getName() +
                ".duplicate(L) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return null;
    }
    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public int cardinal() {
        System.err.println("Error :: "+this.getClass().getName() +
                ".duplicate(L) is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return -1;
    }
    /**
     * This method is not defined for class {@link LatticeCommon } and generates
     * an error report and a code exit.
     * <BR><BR>
     * <b>Documentation inherited from {@link Lattice }:</b><BR>
     * {@inheritDoc}
     */
    public L instance() {
        System.err.println("Error :: "+this.getClass().getName() +
                ".instance() is not implemented!");
        System.err.println("Please implement instance() in your class with @Override");
        System.exit(0);
        return null;
    }

    public L instanceNsize() { // create an instance with the same size
        return instance().size((L) this);
    }

    @Override
    public L clone() {
        return instance().duplicate((L) this);
    }

}
