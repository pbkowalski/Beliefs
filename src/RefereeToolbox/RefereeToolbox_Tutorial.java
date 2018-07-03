/*
 *   RefereeToolbox_Tutorial.java : part of package RefereeToolbox; Tutorial.
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

// juste pour tests
import java.util.*;

/**
 * Class providing a tutorial for package RefereeToolbox.
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
public class RefereeToolbox_Tutorial{

    /**
     * Compare different referee functions applied with RefereeFuser and Powerset.
     *
     * <BR><BR>
     * <b>Detailed code description.</b><BR>
     * The following typonomic conventions are used:<BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Blue color is used for commenting the following code.
     * </font><BR>
     * code formating is used for printing the code.<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * Red color is used for printing the possible output resulting of the
     * previous code.
     * </font>
     * </code><BR>
     * <BR><BR>
     * Commented code:
     * <BR><BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of propositon <i>A</i> as class <i>finalPowerset</i> and its 
     * creation;
     * </font><BR>
     * finalPowerset A=new finalPowerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Set the size of the powerset to <i>3</i>; Then set <i>A</i> to the atomic
     * proposition indiced by <i>0</i>;
     * </font><BR>
     * A.size(3); A.atomic(0);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>B</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>B</i> to the atomic proposition indiced
     * by <i>1</i>;
     * </font><BR>
     * finalPowerset B=A.instanceNsize(); B.atomic(1);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>C</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>C</i> to the atomic proposition indiced
     * by <i>2</i>;
     * </font><BR>
     * finalPowerset C=A.instanceNsize(); C.atomic(2);<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>AUB</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>AUB</i> to <i>A OR B</i>;
     * </font><BR>
     * finalPowerset AUB=A.instanceNsize(); AUB.or(A,B);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>BUC</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>BUC</i> to <i>B OR C</i>;
     * <BR>
     * Declaration of <i>CUA</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>CUA</i> to <i>C OR A</i>;
     * </font><BR>
     * finalPowerset BUC=A.instanceNsize(); BUC.or(B,C);<BR>
     * finalPowerset CUA=A.instanceNsize(); CUA.or(C,A);<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>zero</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>zero</i> to zero;
     * <BR>
     * Declaration of <i>one</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>one</i> to one;
     * </font><BR>
     * finalPowerset zero=A.instanceNsize(); zero.zero();<BR>
     * finalPowerset one=A.instanceNsize(); one.one();<BR>
     * //<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the referee function <i>referee1</i> as a Dempster
     * Shafer referee function on powerset;
     * </font><BR>
     * RFDempster_Powerset referee1 = new RFDempster_Powerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the referee function <i>referee2</i> as a Disjunctive
     * referee function on powerset;<BR>
     * Declaration and creation of the referee function <i>referee3</i> as a
     * Dubois & Prade referee function on powerset;<BR>
     * Declaration and creation of the referee function <i>referee4</i> as a PCR6
     * referee function on powerset;<BR>
     * Declaration and creation of the referee function <i>referee5</i> as a PCR#
     * referee function on powerset;
     * </font><BR>
     * RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();<BR>
     * RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();<BR>
     * RFPCR6_Powerset referee4 = new RFPCR6_Powerset();<BR>
     * RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();<BR>
     * //<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the basic belief assignment <i>aFuser1</i>, a
     * bba defined over powerset and with the capability to handle fusion by means
     * of referee functions (class {@link finalRefereeFuserRTS_Powerset});
     * </font><BR>
     * finalRefereeFuserRTS_Powerset aFuser1 = new finalRefereeFuserRTS_Powerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(A,0.09)</i> to bba <i>aFuser1</i>;
     * </font><BR>
     * aFuser1.add(A,0.09);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(B,0.2)</i> to bba <i>aFuser1</i>;<BR>
     * Add assignment <i>(C,0.02)</i> to bba <i>aFuser1</i>;<BR>
     * Add assignment <i>(AUB,0.05)</i> to bba <i>aFuser1</i>;<BR>
     * Add assignment <i>(BUC,0.03)</i> to bba <i>aFuser1</i>;<BR>
     * Add assignment <i>(CUA,0.1)</i> to bba <i>aFuser1</i>;
     * </font><BR>
     * aFuser1.add(B,0.2);<BR>
     * aFuser1.add(C,0.02);<BR>
     * aFuser1.add(AUB,0.05);<BR>
     * aFuser1.add(BUC,0.03);<BR>
     * aFuser1.add(CUA,0.1);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(A,0.11)</i> to bba <i>aFuser1</i>. This assignment is
     * <i>added</i> to previewsly added assignment <i>(A,0.09)</i>, resulting in
     * total assignment <i>(A,0.2)</i>;
     * </font><BR>
     * aFuser1.add(A,0.11);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(one,0.4)</i> to bba <i>aFuser1</i>;
     * </font><BR>
     * aFuser1.add(one,0.4);<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the basic belief assignment <i>aFuser2</i>, a
     * bba defined over powerset and with the capability to handle fusion by means
     * of referee functions (class {@link finalRefereeFuserRTS_Powerset});
     * </font><BR>
     * finalRefereeFuserRTS_Powerset aFuser2 = aFuser1.instance();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(A,0.1)</i> to bba <i>aFuser2</i>;<BR>
     * Add assignment <i>(B,0.1)</i> to bba <i>aFuser2</i>;<BR>
     * Add assignment <i>(C,0.2)</i> to bba <i>aFuser2</i>;<BR>
     * Add assignment <i>(AUB,0.2)</i> to bba <i>aFuser2</i>;<BR>
     * Add assignment <i>(BUC,0.1)</i> to bba <i>aFuser2</i>;<BR>
     * Add assignment <i>(CUA,0.1)</i> to bba <i>aFuser2</i>;<BR>
     * Add assignment <i>(one,0.2)</i> to bba <i>aFuser2</i>;
     * </font><BR>
     * aFuser2.add(A,0.1);<BR>
     * aFuser2.add(B,0.1);<BR>
     * aFuser2.add(C,0.2);<BR>
     * aFuser2.add(AUB,0.2);<BR>
     * aFuser2.add(BUC,0.1);<BR>
     * aFuser2.add(CUA,0.1);<BR>
     * aFuser2.add(one,0.2);<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the basic belief assignment <i>aFuser</i>, a
     * bba defined over powerset and with the capability to handle fusion by means
     * of referee functions (class {@link finalRefereeFuserRTS_Powerset});
     * </font><BR>
     * finalRefereeFuserRTS_Powerset aFuser = aFuser1.instance();<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Define a common print mode <i>printMode</i> for the bba state.
     * This variable, set to <i>1</i>, implies the print to be according to the
     * proposition order;
     * </font><BR>
     * int printMode=1;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the title of the method;
     * </font><BR>
     * System.out.println(<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;
     * "/////////////////////////////\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;
     * "// Relaxed Method\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;
     * "////////////////////\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * /////////////////////////////<BR>
     * // Relaxed Method<BR>
     * ////////////////////
     * </font><BR>
     * System.out.println("aFuser1");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aFuser1
     * </font><BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aFuser1</i>. With option <i>printMode=1</i>, the print is
     * done according to the proposition order;
     * </font><BR>
     * &nbsp; &nbsp; 
     * System.out.println(aFuser1.state(printMode));<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.2<BR>
     * x0000000000000002 -> 0.2<BR>
     * x0000000000000003 -> 0.05<BR>
     * x0000000000000004 -> 0.02<BR>
     * x0000000000000005 -> 0.1<BR>
     * x0000000000000006 -> 0.03<BR>
     * x0000000000000007 -> 0.4
     * </font><BR>
     * &nbsp; &nbsp; 
     * System.out.println();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aFuser2</i>. With option <i>printMode=1</i>, the print is
     * done according to the proposition order;
     * </font><BR>
     * System.out.println("aFuser2");<BR>
     * &nbsp; &nbsp; 
     * System.out.println(aFuser2.state(printMode));<BR>
     * &nbsp; &nbsp; 
     * System.out.println();<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aFuser2<BR>
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.1<BR>
     * x0000000000000002 -> 0.1<BR>
     * x0000000000000003 -> 0.2<BR>
     * x0000000000000004 -> 0.2<BR>
     * x0000000000000005 -> 0.1<BR>
     * x0000000000000006 -> 0.1<BR>
     * x0000000000000007 -> 0.2
     * </font><BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Set <i>aFuser</i> as the fusion of <i>aFuser1</i> and <i>aFuser2</i>, according
     * to the referee function <i>referee1</i>. As a result, a Dempster Shafer
     * combination is done;
     * </font><BR>
     * aFuser.fuse(aFuser1,aFuser2,referee1);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aFuser</i>. With option <i>printMode=1</i>, the print is
     * done according to the proposition order;
     * </font><BR>
     * &nbsp; &nbsp;
     * System.out.println("aFuser - Dempster");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+aFuser.conflict()+" %");
     * &nbsp; &nbsp;
     * System.out.println(aFuser.state(printMode));<BR>
     * &nbsp; &nbsp;
     * System.out.println();<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aFuser - Dempster<BR>
     * Conflict Z = 0.19100000000000006 %
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.24721878862793575<BR>
     * x0000000000000002 -> 0.2212608158220025<BR>
     * x0000000000000003 -> 0.12360939431396788<BR>
     * x0000000000000004 -> 0.16192830655129792<BR>
     * x0000000000000005 -> 0.08652657601977749<BR>
     * x0000000000000006 -> 0.06056860321384425<BR>
     * x0000000000000007 -> 0.09888751545117429
     * </font><BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Set <i>aFuser</i> as the fusion of <i>aFuser1</i> and <i>aFuser2</i>, according
     * to the referee function <i>referee2</i>. As a result, a disjunctive
     * combination is done;
     * </font><BR>
     * aFuser.fuse(aFuser1,aFuser2,referee2);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aFuser</i>. With option <i>printMode=1</i>, the print is
     * done according to the proposition order;
     * </font><BR>
     * &nbsp; &nbsp;
     * System.out.println("aFuser - Disjunctive");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+aFuser.conflict()+" %");
     * &nbsp; &nbsp;
     * System.out.println(aFuser.state(printMode));<BR>
     * &nbsp; &nbsp;
     * System.out.println();<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aFuser - Disjunctive<BR>
     * Conflict Z = 0.0 %
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.019999999999999993<BR>
     * x0000000000000002 -> 0.019999999999999993<BR>
     * x0000000000000003 -> 0.13999999999999999<BR>
     * x0000000000000004 -> 0.003999999999999998<BR>
     * x0000000000000005 -> 0.10399999999999997<BR>
     * x0000000000000006 -> 0.07599999999999998<BR>
     * x0000000000000007 -> 0.636
     * </font><BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Set <i>aFuser</i> as the fusion of <i>aFuser1</i> and <i>aFuser2</i>, according
     * to the referee function <i>referee3</i>. As a result, a Dubois & Prade
     * combination is done;
     * </font><BR>
     * aFuser.fuse(aFuser1,aFuser2,referee3);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aFuser</i>. With option <i>printMode=1</i>, the print is
     * done according to the proposition order;
     * </font><BR>
     * &nbsp; &nbsp;
     * System.out.println("aFuser - Dubois & Prade");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+aFuser.conflict()+" %");
     * &nbsp; &nbsp;
     * System.out.println(aFuser.state(printMode));<BR>
     * &nbsp; &nbsp;
     * System.out.println();<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aFuser - Dubois & Prade<BR>
     * Conflict Z = 0.0 %
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.2<BR>
     * x0000000000000002 -> 0.17900000000000002<BR>
     * x0000000000000003 -> 0.14<BR>
     * x0000000000000004 -> 0.131<BR>
     * x0000000000000005 -> 0.11199999999999999<BR>
     * x0000000000000006 -> 0.09100000000000001<BR>
     * x0000000000000007 -> 0.147
     * </font><BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Set <i>aFuser</i> as the fusion of <i>aFuser1</i> and <i>aFuser2</i>, according
     * to the referee function <i>referee4</i>. As a result, a PCR6 combination
     * is done;
     * </font><BR>
     * aFuser.fuse(aFuser1,aFuser2,referee4);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aFuser</i>. With option <i>printMode=1</i>, the print is
     * done according to the proposition order;
     * </font><BR>
     * &nbsp; &nbsp;
     * System.out.println("aFuser - PCR6");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+aFuser.conflict()+" %");
     * &nbsp; &nbsp;
     * System.out.println(aFuser.state(printMode));<BR>
     * &nbsp; &nbsp;
     * System.out.println();<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aFuser - PCR6<BR>
     * Conflict Z = 0.0 %
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.2573076923076923<BR>
     * x0000000000000002 -> 0.23900000000000005<BR>
     * x0000000000000003 -> 0.10563636363636364<BR>
     * x0000000000000004 -> 0.18003030303030304<BR>
     * x0000000000000005 -> 0.08166666666666668<BR>
     * x0000000000000006 -> 0.056358974358974356<BR>
     * x0000000000000007 -> 0.08
     * </font><BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Set <i>aFuser</i> as the fusion of <i>aFuser1</i> and <i>aFuser2</i>, according
     * to the referee function <i>referee5</i>. As a result, a PCR# combination
     * is done;
     * </font><BR>
     * aFuser.fuse(aFuser1,aFuser2,referee5);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aFuser</i>. With option <i>printMode=1</i>, the print is
     * done according to the proposition order;
     * </font><BR>
     * &nbsp; &nbsp;
     * System.out.println("aFuser - PCR#");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+aFuser.conflict()+" %");
     * &nbsp; &nbsp;
     * System.out.println(aFuser.state(printMode));<BR>
     * &nbsp; &nbsp;
     * System.out.println();<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aFuser - PCR#<BR>
     * Conflict Z = 0.0 %
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.2573076923076923<BR>
     * x0000000000000002 -> 0.23900000000000005<BR>
     * x0000000000000003 -> 0.10563636363636364<BR>
     * x0000000000000004 -> 0.18003030303030304<BR>
     * x0000000000000005 -> 0.08166666666666668<BR>
     * x0000000000000006 -> 0.056358974358974356<BR>
     * x0000000000000007 -> 0.08
     * </font><BR>
     * <BR>
     * </code>
     *
     */
    public void RefereeFuserRTS_Comparison() {
        finalPowerset A=new finalPowerset();
        A.size(3); A.atomic(0);
        finalPowerset B=A.instanceNsize(); B.atomic(1);
        finalPowerset C=A.instanceNsize(); C.atomic(2);
        //
        finalPowerset AUB=A.instanceNsize(); AUB.or(A,B);
        finalPowerset BUC=A.instanceNsize(); BUC.or(B,C);
        finalPowerset CUA=A.instanceNsize(); CUA.or(C,A);
        //
        finalPowerset zero=A.instanceNsize(); zero.zero();
        finalPowerset one=A.instanceNsize(); one.one();
        //
        //
        RFDempster_Powerset referee1 = new RFDempster_Powerset();
        RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();
        RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();
        RFPCR6_Powerset referee4 = new RFPCR6_Powerset();
        RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();
        //
        //
        finalRefereeFuserRTS_Powerset aFuser1 = new finalRefereeFuserRTS_Powerset();
        aFuser1.add(A,0.09);
        aFuser1.add(B,0.2);
        aFuser1.add(C,0.02);
        aFuser1.add(AUB,0.05);
        aFuser1.add(BUC,0.03);
        aFuser1.add(CUA,0.1);
        aFuser1.add(A,0.11);
        aFuser1.add(one,0.4);
        //
        finalRefereeFuserRTS_Powerset aFuser2 = aFuser1.instance();
        aFuser2.add(A,0.1);
        aFuser2.add(B,0.1);
        aFuser2.add(C,0.2);
        aFuser2.add(AUB,0.2);
        aFuser2.add(BUC,0.1);
        aFuser2.add(CUA,0.1);
        aFuser2.add(one,0.2);
        //
        finalRefereeFuserRTS_Powerset aFuser = aFuser1.instance();
//
    
        int printMode=1;

        System.out.println(
                "/////////////////////////////\n" +
                "// Relaxed Method\n" +
                "////////////////////\n");

        System.out.println("aFuser1");
            System.out.println(aFuser1.state(printMode));
            System.out.println();
        System.out.println("aFuser2");
            System.out.println(aFuser2.state(printMode));
            System.out.println();
        aFuser.fuse(aFuser1,aFuser2,referee1);
        System.out.println("aFuser - Dempster");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();
        aFuser.fuse(aFuser1,aFuser2,referee2);
        System.out.println("aFuser - Disjunctive");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();
        aFuser.fuse(aFuser1,aFuser2,referee3);
        System.out.println("aFuser - Dubois & Prade");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();
        aFuser.fuse(aFuser1,aFuser2,referee4);
        System.out.println("aFuser - PCR6");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();
        aFuser.fuse(aFuser1,aFuser2,referee5);
        System.out.println("aFuser - PCR#");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();

    }

    /**
     * Compare different referee functions applied with RefereeSampler and Powerset.
     *
     * <BR><BR>
     * <b>Detailed code description.</b><BR>
     * The following typonomic conventions are used:<BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Blue color is used for commenting the following code.
     * </font><BR>
     * code formating is used for printing the code.<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * Red color is used for printing the possible output resulting of the
     * previous code.
     * </font>
     * </code><BR>
     * <BR><BR>
     * Commented code:
     * <BR><BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of propositon <i>A</i> as class <i>finalPowerset</i> and its
     * creation;
     * </font><BR>
     * finalPowerset A=new finalPowerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Set the size of the powerset to <i>3</i>; Then set <i>A</i> to the atomic
     * proposition indiced by <i>0</i>;
     * </font><BR>
     * A.size(3); A.atomic(0);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>B</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>B</i> to the atomic proposition indiced
     * by <i>1</i>;
     * </font><BR>
     * finalPowerset B=A.instanceNsize(); B.atomic(1);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>C</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>C</i> to the atomic proposition indiced
     * by <i>2</i>;
     * </font><BR>
     * finalPowerset C=A.instanceNsize(); C.atomic(2);<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>AUB</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>AUB</i> to <i>A OR B</i>;
     * </font><BR>
     * finalPowerset AUB=A.instanceNsize(); AUB.or(A,B);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>BUC</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>BUC</i> to <i>B OR C</i>;
     * <BR>
     * Declaration of <i>CUA</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>CUA</i> to <i>C OR A</i>;
     * </font><BR>
     * finalPowerset BUC=A.instanceNsize(); BUC.or(B,C);<BR>
     * finalPowerset CUA=A.instanceNsize(); CUA.or(C,A);<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of <i>zero</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>zero</i> to zero;
     * <BR>
     * Declaration of <i>one</i> as <i>finalPowerset</i> and its creation and sizing
     * as instance of <i>A</i>; Then set <i>one</i> to one;
     * </font><BR>
     * finalPowerset zero=A.instanceNsize(); zero.zero();<BR>
     * finalPowerset one=A.instanceNsize(); one.one();<BR>
     * //<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the referee function <i>referee1</i> as a Dempster
     * Shafer referee function on powerset;
     * </font><BR>
     * RFDempster_Powerset referee1 = new RFDempster_Powerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the referee function <i>referee2</i> as a Disjunctive
     * referee function on powerset;<BR>
     * Declaration and creation of the referee function <i>referee3</i> as a
     * Dubois & Prade referee function on powerset;<BR>
     * Declaration and creation of the referee function <i>referee4</i> as a PCR6
     * referee function on powerset;<BR>
     * Declaration and creation of the referee function <i>referee5</i> as a PCR#
     * referee function on powerset;
     * </font><BR>
     * RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();<BR>
     * RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();<BR>
     * RFPCR6_Powerset referee4 = new RFPCR6_Powerset();<BR>
     * RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();<BR>
     * //<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the basic belief assignment <i>aSampler1</i>, a
     * bba defined over powerset and with the capability to handle fusion by means
     * of referee sampling (class {@link finalRefereeSampler_Powerset});
     * </font><BR>
     * finalRefereeSampler_Powerset aSampler1 = new finalRefereeSampler_Powerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(A,0.09)</i> to bba <i>aSampler1</i>;
     * </font><BR>
     * aSampler1.add(A,0.09);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(B,0.2)</i> to bba <i>aSampler1</i>;<BR>
     * Add assignment <i>(C,0.02)</i> to bba <i>aSampler1</i>;<BR>
     * Add assignment <i>(AUB,0.05)</i> to bba <i>aSampler1</i>;<BR>
     * Add assignment <i>(BUC,0.03)</i> to bba <i>aSampler1</i>;<BR>
     * Add assignment <i>(CUA,0.1)</i> to bba <i>aSampler1</i>;
     * </font><BR>
     * aSampler1.add(B,0.2);<BR>
     * aSampler1.add(C,0.02);<BR>
     * aSampler1.add(AUB,0.05);<BR>
     * aSampler1.add(BUC,0.03);<BR>
     * aSampler1.add(CUA,0.1);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(A,0.11)</i> to bba <i>aSampler1</i>. This assignment is
     * <i>added</i> to previewsly added assignment <i>(A,0.09)</i>, resulting in
     * total assignment <i>(A,0.2)</i>;
     * </font><BR>
     * aSampler1.add(A,0.11);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(one,0.4)</i> to bba <i>aSampler1</i>;
     * </font><BR>
     * aSampler1.add(one,0.4);<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the basic belief assignment <i>aSampler2</i>, a
     * bba defined over powerset and with the capability to handle fusion by means
     * of referee sampling (class {@link finalRefereeSampler_Powerset});
     * </font><BR>
     * finalRefereeSampler_Powerset aSampler2 = aSampler1.instance();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Add assignment <i>(A,0.1)</i> to bba <i>aSampler2</i>;<BR>
     * Add assignment <i>(B,0.1)</i> to bba <i>aSampler2</i>;<BR>
     * Add assignment <i>(C,0.2)</i> to bba <i>aSampler2</i>;<BR>
     * Add assignment <i>(AUB,0.2)</i> to bba <i>aSampler2</i>;<BR>
     * Add assignment <i>(BUC,0.1)</i> to bba <i>aSampler2</i>;<BR>
     * Add assignment <i>(CUA,0.1)</i> to bba <i>aSampler2</i>;<BR>
     * Add assignment <i>(one,0.2)</i> to bba <i>aSampler2</i>;
     * </font><BR>
     * aSampler2.add(A,0.1);<BR>
     * aSampler2.add(B,0.1);<BR>
     * aSampler2.add(C,0.2);<BR>
     * aSampler2.add(AUB,0.2);<BR>
     * aSampler2.add(BUC,0.1);<BR>
     * aSampler2.add(CUA,0.1);<BR>
     * aSampler2.add(one,0.2);<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the basic belief assignment <i>aSampler</i>, a
     * bba defined over powerset and with the capability to handle fusion by means
     * of referee sampling (class {@link finalRefereeSampler_Powerset});
     * </font><BR>
     * finalRefereeSampler_Powerset aSampler = aSampler1.instance();<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Define a common print mode <i>printMode</i> for the bba state.
     * This variable, set to <i>1</i>, implies the print to be according to the
     * proposition order;
     * </font><BR>
     * int printMode=1;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the title of the method;
     * </font><BR>
     * System.out.println(<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;
     * "/////////////////////////////\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;
     * "// Sampling Method\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp;
     * "////////////////////\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * /////////////////////////////<BR>
     * // Sampling Method<BR>
     * ////////////////////
     * </font><BR>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * Variables declaration.
     * <i>n</i> and <i>Z</i> are instrumental and used for sample indices and contradiction
     * degree repectively.
     * <i>NbSamples</i> stores the number of samples generated by each computation;
     * </font><BR>
     * int n;<BR>
     * int NbSamples=1000000;<BR>
     * double Z;<BR>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration and creation of the basic belief assignment <i>Samples</i>. This
     * bba is instrumental and used for computing the samples statistic incrementally;
     * </font><BR>
     * finalRefereeSampler_Powerset Samples= aSampler1.instance();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aSampler1</i>. With option <i>printMode=1</i>, the print is
     * done according to the proposition order;
     * </font><BR>
     * System.out.println("aSampler1");<BR>
     * &nbsp; &nbsp; System.out.println(aSampler1.state(printMode));<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aSampler1<BR>
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.2<BR>
     * x0000000000000002 -> 0.2<BR>
     * x0000000000000003 -> 0.05<BR>
     * x0000000000000004 -> 0.02<BR>
     * x0000000000000005 -> 0.1<BR>
     * x0000000000000006 -> 0.03<BR>
     * x0000000000000007 -> 0.4
     * </font><BR>
     * &nbsp; &nbsp; System.out.println();<BR>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * Print the state of <i>aSampler2</i>. With option <i>printMode=1</i>, the
     * print is done according to the proposition order;
     * </font><BR>
     * System.out.println("aSampler2");<BR>
     * &nbsp; &nbsp; System.out.println(aSampler2.state(printMode));<BR>
     * &nbsp; &nbsp; System.out.println();<BR>
     * //<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aSampler2<BR>
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.1<BR>
     * x0000000000000002 -> 0.1<BR>
     * x0000000000000003 -> 0.2<BR>
     * x0000000000000004 -> 0.2<BR>
     * x0000000000000005 -> 0.1<BR>
     * x0000000000000006 -> 0.1<BR>
     * x0000000000000007 -> 0.2
     * </font><BR>
     * 
     * <font color="#0000FF" style="font-family: georgia">
     * Initialize the fuser of <i>aSampler</i> with bba enties <i>aFuser1</i> and
     * <i>aFuser2</i>, and with the referee function <i>referee1</i> (Dempster Shafer);
     * </font><BR>
     * aSampler.setFuser(aSampler1, aSampler2, referee1);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Clear the samples container <i>Samples</i>;
     * </font><BR>
     * Samples.clear();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Build the samples (<i>NbSamples</i> times) fused by means of the fuser and
     * store them within the container;
     * </font><BR>
     * {@code for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());}<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Learn the bba <i>aSampler</i> from the samples <i>Samples</i> and store the
     * percentage of conflict samples within <i>Z</i>;
     * </font><BR>
     * Z=aSampler.learnFrom(Samples.toArray());<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the conflict and the state of <i>aSampler</i> (these results are random).
     * With option <i>printMode=1</i>, the print is done according to the proposition
     * order;
     * </font><BR>
     * System.out.println("aSampler - Dempster -- " + NbSamples + " particles");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+Z+" %");<BR>
     * &nbsp; &nbsp; System.out.println(aSampler.state(printMode));<BR>
     * &nbsp; &nbsp; System.out.println();<BR>
     * &nbsp; &nbsp; //<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aSampler - Dempster -- 1000000 particles<BR>
     * Conflict Z = 0.190523 %<BR>
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.2474844868970953<BR>
     * x0000000000000002 -> 0.2219408334023079<BR>
     * x0000000000000003 -> 0.1233846051215785<BR>
     * x0000000000000004 -> 0.16099654468255428<BR>
     * x0000000000000005 -> 0.08678813604339593<BR>
     * x0000000000000006 -> 0.06061197538657676<BR>
     * x0000000000000007 -> 0.09879341846649133<BR>
     * </font><BR>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * Initialize the fuser of <i>aSampler</i> with bba enties <i>aFuser1</i> and
     * <i>aFuser2</i>, and with the referee function <i>referee2</i> (Disjunctive);
     * </font><BR>
     * aSampler.setFuser(aSampler1, aSampler2, referee2);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Clear the samples container <i>Samples</i>;
     * </font><BR>
     * Samples.clear();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Build the samples (<i>NbSamples</i> times) fused by means of the fuser and
     * store them within the container;
     * </font><BR>
     * {@code for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());}<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Learn the bba <i>aSampler</i> from the samples <i>Samples</i> and store the
     * percentage of conflict samples within <i>Z</i>;
     * </font><BR>
     * Z=aSampler.learnFrom(Samples.toArray());<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the conflict and the state of <i>aSampler</i> (these results are random).
     * With option <i>printMode=1</i>, the print is done according to the proposition
     * order;
     * </font><BR>
     * System.out.println("aSampler - Disjunctive -- " + NbSamples + " particles");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+Z+" %");<BR>
     * &nbsp; &nbsp; System.out.println(aSampler.state(printMode));<BR>
     * &nbsp; &nbsp; System.out.println();<BR>
     * &nbsp; &nbsp; //<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aSampler - Disjunctive -- 1000000 particles<BR>
     * Conflict Z = 0.0 %<BR>
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.01991<BR>
     * x0000000000000002 -> 0.019988<BR>
     * x0000000000000003 -> 0.139937<BR>
     * x0000000000000004 -> 0.004052<BR>
     * x0000000000000005 -> 0.103861<BR>
     * x0000000000000006 -> 0.075948<BR>
     * x0000000000000007 -> 0.636304
     * </font><BR>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * Initialize the fuser of <i>aSampler</i> with bba enties <i>aFuser1</i> and
     * <i>aFuser2</i>, and with the referee function <i>referee3</i> (Dubois & Prade);
     * </font><BR>
     * aSampler.setFuser(aSampler1, aSampler2, referee3);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Clear the samples container <i>Samples</i>;
     * </font><BR>
     * Samples.clear();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Build the samples (<i>NbSamples</i> times) fused by means of the fuser and
     * store them within the container;
     * </font><BR>
     * {@code for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());}<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Learn the bba <i>aSampler</i> from the samples <i>Samples</i> and store the
     * percentage of conflict samples within <i>Z</i>;
     * </font><BR>
     * Z=aSampler.learnFrom(Samples.toArray());<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the conflict and the state of <i>aSampler</i> (these results are random).
     * With option <i>printMode=1</i>, the print is done according to the proposition
     * order;
     * </font><BR>
     * System.out.println("aSampler - Dubois & Prade -- " + NbSamples + " particles");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+Z+" %");<BR>
     * &nbsp; &nbsp; System.out.println(aSampler.state(printMode));<BR>
     * &nbsp; &nbsp; System.out.println();<BR>
     * &nbsp; &nbsp; //<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aSampler - Dubois & Prade -- 1000000 particles<BR>
     * Conflict Z = 0.0 %<BR>
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.200409<BR>
     * x0000000000000002 -> 0.179814<BR>
     * x0000000000000003 -> 0.139608<BR>
     * x0000000000000004 -> 0.130784<BR>
     * x0000000000000005 -> 0.111702<BR>
     * x0000000000000006 -> 0.090511<BR>
     * x0000000000000007 -> 0.147172
     * </font><BR>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * Initialize the fuser of <i>aSampler</i> with bba enties <i>aFuser1</i> and
     * <i>aFuser2</i>, and with the referee function <i>referee4</i> (PCR6);
     * </font><BR>
     * aSampler.setFuser(aSampler1, aSampler2, referee4);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Clear the samples container <i>Samples</i>;
     * </font><BR>
     * Samples.clear();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Build the samples (<i>NbSamples</i> times) fused by means of the fuser and
     * store them within the container;
     * </font><BR>
     * {@code for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());}<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Learn the bba <i>aSampler</i> from the samples <i>Samples</i> and store the
     * percentage of conflict samples within <i>Z</i>;
     * </font><BR>
     * Z=aSampler.learnFrom(Samples.toArray());<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the conflict and the state of <i>aSampler</i> (these results are random).
     * With option <i>printMode=1</i>, the print is done according to the proposition
     * order;
     * </font><BR>
     * System.out.println("aSampler - PCR6 -- " + NbSamples + " particles");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+Z+" %");<BR>
     * &nbsp; &nbsp; System.out.println(aSampler.state(printMode));<BR>
     * &nbsp; &nbsp; System.out.println();<BR>
     * &nbsp; &nbsp; //<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aSampler - PCR6 -- 1000000 particles<BR>
     * Conflict Z = 0.0 %<BR>
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.25761<BR>
     * x0000000000000002 -> 0.238275<BR>
     * x0000000000000003 -> 0.105398<BR>
     * x0000000000000004 -> 0.179867<BR>
     * x0000000000000005 -> 0.081813<BR>
     * x0000000000000006 -> 0.056784<BR>
     * x0000000000000007 -> 0.080253<BR>
     * </font><BR>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * Initialize the fuser of <i>aSampler</i> with bba enties <i>aFuser1</i> and
     * <i>aFuser2</i>, and with the referee function <i>referee4</i> (PCR#);
     * </font><BR>
     * aSampler.setFuser(aSampler1, aSampler2, referee5);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Clear the samples container <i>Samples</i>;
     * </font><BR>
     * Samples.clear();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Build the samples (<i>NbSamples</i> times) fused by means of the fuser and
     * store them within the container;
     * </font><BR>
     * {@code for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());}<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Learn the bba <i>aSampler</i> from the samples <i>Samples</i> and store the
     * percentage of conflict samples within <i>Z</i>;
     * </font><BR>
     * Z=aSampler.learnFrom(Samples.toArray());<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the conflict and the state of <i>aSampler</i> (these results are random).
     * With option <i>printMode=1</i>, the print is done according to the proposition
     * order;
     * </font><BR>
     * System.out.println("aSampler - PCR# -- " + NbSamples + " particles");<BR>
     * &nbsp; &nbsp; System.out.println("Conflict Z = "+Z+" %");<BR>
     * &nbsp; &nbsp; System.out.println(aSampler.state(printMode));<BR>
     * &nbsp; &nbsp; System.out.println();<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * aSampler - PCR# -- 1000000 particles<BR>
     * Conflict Z = 0.0 %<BR>
     * bbaTreeA:<BR>
     * x0000000000000001 -> 0.257286<BR>
     * x0000000000000002 -> 0.239343<BR>
     * x0000000000000003 -> 0.105115<BR>
     * x0000000000000004 -> 0.179943<BR>
     * x0000000000000005 -> 0.08186<BR>
     * x0000000000000006 -> 0.056558<BR>
     * x0000000000000007 -> 0.079895
     * </font><BR>
     *
     * </code>
     *
     */
    public void RefereeSampler_Comparison() {
        finalPowerset A=new finalPowerset();
        A.size(3); A.atomic(0);
        finalPowerset B=A.instanceNsize(); B.atomic(1);
        finalPowerset C=A.instanceNsize(); C.atomic(2);
        //
        finalPowerset AUB=A.instanceNsize(); AUB.or(A,B);
        finalPowerset BUC=A.instanceNsize(); BUC.or(B,C);
        finalPowerset CUA=A.instanceNsize(); CUA.or(C,A);
        //
        finalPowerset zero=A.instanceNsize(); zero.zero();
        finalPowerset one=A.instanceNsize(); one.one();
        //
        //
        RFDempster_Powerset referee1 = new RFDempster_Powerset();
        RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();
        RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();
        RFPCR6_Powerset referee4 = new RFPCR6_Powerset();
        RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();
        //
        //
        finalRefereeSampler_Powerset aSampler1 = new finalRefereeSampler_Powerset();
        aSampler1.add(A,0.09);
        aSampler1.add(B,0.2);
        aSampler1.add(C,0.02);
        aSampler1.add(AUB,0.05);
        aSampler1.add(BUC,0.03);
        aSampler1.add(CUA,0.1);
        aSampler1.add(A,0.11);
        aSampler1.add(one,0.4);
        //
        finalRefereeSampler_Powerset aSampler2 = aSampler1.instance();
        aSampler2.add(A,0.1);
        aSampler2.add(B,0.1);
        aSampler2.add(C,0.2);
        aSampler2.add(AUB,0.2);
        aSampler2.add(BUC,0.1);
        aSampler2.add(CUA,0.1);
        aSampler2.add(one,0.2);
        //
        finalRefereeSampler_Powerset aSampler = aSampler1.instance();
//

        int printMode=1;

        System.out.println(
                "/////////////////////////////\n" +
                "// Sampling Method\n" +
                "////////////////////\n");

        int n;
        int NbSamples=1000000;
        double Z;
        finalRefereeSampler_Powerset Samples= aSampler1.instance();

        System.out.println("aSampler1");
            System.out.println(aSampler1.state(printMode));
            System.out.println();
        System.out.println("aSampler2");
            System.out.println(aSampler2.state(printMode));
            System.out.println();
        //
        aSampler.setFuser(aSampler1, aSampler2, referee1);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - Dempster -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();
            //
        aSampler.setFuser(aSampler1, aSampler2, referee2);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - Disjunctive -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();
            //
        aSampler.setFuser(aSampler1, aSampler2, referee3);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - Dubois & Prade -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();
            //
        aSampler.setFuser(aSampler1, aSampler2, referee4);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - PCR6 -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();
            //
        aSampler.setFuser(aSampler1, aSampler2, referee5);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - PCR# -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();

    }

    /**
     * Compare different referee functions applied with RefereeFuser/RefereeSampler and Powerset.
     * 
     */
    public void Referee_Comparison() {
        RefereeFuserRTS_Comparison();
        RefereeSampler_Comparison();
    }

    /**
     * Test some logical manipulations ({@link Freeboolean}, {@link Superpowerset},
     * {@link Openhyperpowerset}, {@link Closedhyperpowerset}, {@link Powerset}).
     *
     */
    public void logical_tests() {
        int theSizeOfFrame = 4;
        int theSizeOfFramePowerset = 8;

        logical_tests_Freeboolean(theSizeOfFrame);
        System.out.println("\n---------------------------------------\n" +
                "---------------------------------------\n");
        logical_tests_Superpowerset(theSizeOfFrame);
        System.out.println("\n---------------------------------------\n" +
                "---------------------------------------\n");
        logical_tests_Openhyperpowerset(theSizeOfFrame);
        System.out.println("\n---------------------------------------\n" +
                "---------------------------------------\n");
        logical_tests_Closedhyperpowerset(theSizeOfFrame);
        System.out.println("\n---------------------------------------\n" +
                "---------------------------------------\n");
        logical_tests_Powerset(theSizeOfFramePowerset);
    }

    /**
     * Test some logical manipulations on Freeboolean.
     *
     * <BR><BR>
     * <b>Detailed code description.</b><BR>
     * The following typonomic conventions are used:<BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Blue color is used for commenting the following code.
     * </font><BR>
     * code formating is used for printing the code.<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * Red color is used for printing the possible output resulting of the
     * previous code.
     * </font>
     * </code><BR><BR>
     * For the output, it is assumed that <i>theSizeOfFrame=4</i> (parameter of the method).
     * <BR><BR>
     * Commented code:
     * <BR><BR>
     * <code>
     * int i;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of a free Boolean algebra, <i>fFb</i>;
     * </font><BR>
     * finalFreeboolean fFb = new finalFreeboolean();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Resize <i>fFb</i> to <i>theSizeOfFrame</i>, the parameter of the method.
     * If the resizing failed, produce an error message and exit;
     * </font><BR>
     * if(fFb.size(theSizeOfFrame)!=theSizeOfFrame) {<BR>
     * &nbsp; &nbsp; System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Freeboolean(int)\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "Exceed authorized bounds!");<BR>
     * &nbsp; &nbsp; System.exit(0);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the title of the method;
     * </font><BR>
     * System.out.println("\n\n////// Test: Freeboolean - size = " + theSizeOfFrame +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "    //\n\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: Freeboolean - size = 4 &nbsp; &nbsp; //<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Create the table of atomic propositions, <i>atomF</i>; <BR>
     * Create each atomic proposition by instancing and resizing from <i>fFb</i>, using
     * {@code fFb.instanceNsize();};<BR>
     * Define each atomic by applying the method {@link GeneratedLattice#atomic(int) }; <BR>
     * </font>
     * finalFreeboolean[] atomF = new finalFreeboolean[theSizeOfFrame];<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; atomF[i]=fFb.instanceNsize();<BR>
     * &nbsp; &nbsp; atomF[i].atomic(i);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print <i>zero</i>, <i>one</i> and the table of atomic propositions
     * (printed with hexadecimal coding);
     * </font><BR>
     * fFb.zero();<BR>
     * System.out.println("zero = "+fFb.state());<BR>
     * fFb.one();<BR>
     * System.out.println("one = "+fFb.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; System.out.println("atomF["+i+"] = " +atomF[i].state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * zero = x0000000000000000<BR>
     * one = x000000000000ffff<BR>
     * atomF[0] = x000000000000aaaa<BR>
     * atomF[1] = x000000000000cccc<BR>
     * atomF[2] = x000000000000f0f0<BR>
     * atomF[3] = x000000000000ff00<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the complement of <i>zero</i>, of <i>one</i> and the table of
     * complements of the atomic propositions. The complements
     * are computed by means of code {@code fFb.complement(atomF[i]); } and
     * stored within <i>fFb</i>;
     * </font><BR>
     * fFb.zero().complement();<BR>
     * System.out.println("complement(zero) = "+fFb.state());<BR>
     * fFb.one().complement();<BR>
     * System.out.println("complement(one) = "+fFb.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fFb.complement(atomF[i]);<BR>
     * &nbsp; &nbsp; System.out.println("complement(atomF["+i+"]) = " +fFb.state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * complement(zero) = x000000000000ffff<BR>
     * complement(one) = x0000000000000000<BR>
     * complement(atomF[0]) = x0000000000005555<BR>
     * complement(atomF[1]) = x0000000000003333<BR>
     * complement(atomF[2]) = x0000000000000f0f<BR>
     * complement(atomF[3]) = x00000000000000ff<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the cocomplement of <i>zero</i>, of <i>one</i> and the table of
     * cocomplements of the atomic propositions. The cocomplements
     * are computed by means of code {@code fFb.cocomplement(atomF[i]); } and
     * stored within <i>fFb</i>;
     * </font><BR>
     * fFb.zero().cocomplement();<BR>
     * System.out.println("cocomplement(zero) = "+fFb.state());<BR>
     * fFb.one().cocomplement();<BR>
     * System.out.println("cocomplement(one) = "+fFb.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fFb.cocomplement(atomF[i]);<BR>
     * &nbsp; &nbsp; System.out.println("cocomplement(atomF["+i+"]) = " +fFb.state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * cocomplement(zero) = x000000000000ffff<BR>
     * cocomplement(one) = x0000000000000000<BR>
     * cocomplement(atomF[0]) = x0000000000005555<BR>
     * cocomplement(atomF[1]) = x0000000000003333<BR>
     * cocomplement(atomF[2]) = x0000000000000f0f<BR>
     * cocomplement(atomF[3]) = x00000000000000ff<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * The following codes (21 lines) tests the property <i>(A OR B) AND (B OR C) = B OR (A AND C )</i>,
     * and print the results of the test.
     * Propositions <i>A, B, C</i> are defined respectively as the <i>(theSizeOfFrame/4)</i>-th,
     *  <i>(theSizeOfFrame/2)</i>-th, <i>(3*theSizeOfFrame/4)</i>-th atomics propositions.
     * </font><BR>
     * System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: (A OR B) AND (B OR C) = B OR (A AND C )<BR>
     * </font>
     * finalFreeboolean A = atomF[theSizeOfFrame/4].clone();
     * <font color="#0000FF" style="font-family: georgia">
     * // Method {@link Lattice#clone() } create an exact copy of the proposition</font><BR>
     * finalFreeboolean B = atomF[theSizeOfFrame/2].clone();<BR>
     * finalFreeboolean C = atomF[(3*theSizeOfFrame)/4].clone();<BR>
     * finalFreeboolean AUB = fFb.instanceNsize();<BR>
     * finalFreeboolean BUC = fFb.instanceNsize();<BR>
     * finalFreeboolean ANC = fFb.instanceNsize();<BR>
     * finalFreeboolean left = fFb.instanceNsize();<BR>
     * finalFreeboolean right = fFb.instanceNsize();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A OR B</i> and store it within <i>AUB</i>;<BR>
     * Compute <i>B OR C</i> and store it within <i>BUC</i>;<BR>
     * </font>
     * AUB.or(A, B);<BR>
     * BUC.or(B, C);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A AND C</i> and store it within <i>ANC</i>;<BR>
     * Compute <i>AUB AND BUC</i> and store it within <i>left</i>;<BR>
     * Compute <i>B OR ANC</i> and store it within <i>right</i>;<BR>
     * </font>
     * ANC.and(A, C);<BR>
     * left.and(AUB,BUC);<BR>
     * right.or(B,ANC);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Test if <i>left</i> and <i>right</i> are equal, and store the result within
     * <i>isEqual</i>;<BR>
     * </font>
     * boolean isEqual=left.compareTo(right)==0;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print propositions <i>A,B,C,left,right</i> and the boolean <i>isEqual</i>;<BR>
     * </font>
     * System.out.println("A = atomF["+theSizeOfFrame/4+"] = " + A.state());<BR>
     * System.out.println("B = atomF["+theSizeOfFrame/2+"] = " + B.state());<BR>
     * System.out.println("C = atomF["+(3*theSizeOfFrame)/4+"] = " + C.state());<BR>
     * System.out.println("(A OR B) AND (B OR C)  = " + left.state());<BR>
     * System.out.println("B OR (A AND C ) = " + right.state());<BR>
     * System.out.println("isEqual = "+isEqual);<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * A = atomF[1] = x000000000000cccc<BR>
     * B = atomF[2] = x000000000000f0f0<BR>
     * C = atomF[3] = x000000000000ff00<BR>
     * (A OR B) AND (B OR C)  = x000000000000fcf0<BR>
     * B OR (A AND C ) = x000000000000fcf0<BR>
     * isEqual = true<BR>
     * </font>
     * </code>
     *
     */
    public void logical_tests_Freeboolean(int theSizeOfFrame) {

        int i;
        finalFreeboolean fFb = new finalFreeboolean();
        if(fFb.size(theSizeOfFrame)!=theSizeOfFrame) {
            System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Freeboolean(int)\n" +
                    "Exceed authorized bounds!");
            System.exit(0);
        }
        System.out.println("\n\n////// Test: Freeboolean - size = " + theSizeOfFrame +
                    "    //\n\n");
        finalFreeboolean[] atomF = new finalFreeboolean[theSizeOfFrame];
        for(i=0;i<theSizeOfFrame;i++) {
            atomF[i]=fFb.instanceNsize();
            atomF[i].atomic(i);
        }
        fFb.zero();
        System.out.println("zero = "+fFb.state());
        fFb.one();
        System.out.println("one = "+fFb.state());
        for(i=0;i<theSizeOfFrame;i++) {
            System.out.println("atomF["+i+"] = " +atomF[i].state());
        }
        System.out.println("\n---------------------------------------\n");
        fFb.zero().complement();
        System.out.println("complement(zero) = "+fFb.state());
        fFb.one().complement();
        System.out.println("complement(one) = "+fFb.state());
        for(i=0;i<theSizeOfFrame;i++) {
            fFb.complement(atomF[i]);
            System.out.println("complement(atomF["+i+"]) = " +fFb.state());
        }
        System.out.println("\n---------------------------------------\n");
        fFb.zero().cocomplement();
        System.out.println("cocomplement(zero) = "+fFb.state());
        fFb.one().cocomplement();
        System.out.println("cocomplement(one) = "+fFb.state());
        for(i=0;i<theSizeOfFrame;i++) {
            fFb.cocomplement(atomF[i]);
            System.out.println("cocomplement(atomF["+i+"]) = " +fFb.state());
        }
        System.out.println("\n---------------------------------------\n");

        System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");
        finalFreeboolean A = atomF[theSizeOfFrame/4].clone();
        finalFreeboolean B = atomF[theSizeOfFrame/2].clone();
        finalFreeboolean C = atomF[(3*theSizeOfFrame)/4].clone();
        finalFreeboolean AUB = fFb.instanceNsize();
        finalFreeboolean BUC = fFb.instanceNsize();
        finalFreeboolean ANC = fFb.instanceNsize();
        finalFreeboolean left = fFb.instanceNsize();
        finalFreeboolean right = fFb.instanceNsize();
        AUB.or(A, B);
        BUC.or(B, C);
        ANC.and(A, C);
        left.and(AUB,BUC);
        right.or(B,ANC);
        boolean isEqual=left.compareTo(right)==0;
        System.out.println("A = atomF["+theSizeOfFrame/4+"] = " + A.state());
        System.out.println("B = atomF["+theSizeOfFrame/2+"] = " + B.state());
        System.out.println("C = atomF["+(3*theSizeOfFrame)/4+"] = " + C.state());
        System.out.println("(A OR B) AND (B OR C)  = " + left.state());
        System.out.println("B OR (A AND C ) = " + right.state());
        System.out.println("isEqual = "+isEqual);

    }

    /**
     * Test some logical manipulations on Superpowerset.
     *
     * <BR><BR>
     * <b>Detailed code description.</b><BR>
     * The following typonomic conventions are used:<BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Blue color is used for commenting the following code.
     * </font><BR>
     * code formating is used for printing the code.<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * Red color is used for printing the possible output resulting of the
     * previous code.
     * </font>
     * </code><BR><BR>
     * For the output, it is assumed that <i>theSizeOfFrame=4</i> (parameter of the method).
     * <BR><BR>
     * Commented code:
     * <BR><BR>
     * <code>
     * int i;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of a superpowerset, <i>fSps</i>;
     * </font><BR>
     * finalSuperpowerset fSps = new finalSuperpowerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Resize <i>fSps</i> to <i>theSizeOfFrame</i>, the parameter of the method.
     * If the resizing failed, produce an error message and exit;
     * </font><BR>
     * if(fSps.size(theSizeOfFrame)!=theSizeOfFrame) {<BR>
     * &nbsp; &nbsp; System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Superpowerset(int)\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "Exceed authorized bounds!");<BR>
     * &nbsp; &nbsp; System.exit(0);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the title of the method;
     * </font><BR>
     * System.out.println("\n\n////// Test: Superpowerset - size = " + theSizeOfFrame +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "    //\n\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: Superpowerset - size = 4 &nbsp; &nbsp; //<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Create the table of atomic propositions, <i>atomS</i>; <BR>
     * Create each atomic proposition by instancing and resizing from <i>fSps</i>, using
     * {@code fSps.instanceNsize();};<BR>
     * Define each atomic by applying the method {@link GeneratedLattice#atomic(int) }; <BR>
     * </font>
     * finalSuperpowerset[] atomS = new finalSuperpowerset[theSizeOfFrame];<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; atomS[i]=fSps.instanceNsize();<BR>
     * &nbsp; &nbsp; atomS[i].atomic(i);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print <i>zero</i>, <i>one</i> and the table of atomic propositions (printed
     * with hexadecimal coding);
     * </font><BR>
     * fSps.zero();<BR>
     * System.out.println("zero = "+fSps.state());<BR>
     * fSps.one();<BR>
     * System.out.println("one = "+fSps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; System.out.println("atomS["+i+"] = " +atomS[i].state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * zero = x0000000000000000<BR>
     * one = x000000000000fffe<BR>
     * atomS[0] = x000000000000aaaa<BR>
     * atomS[1] = x000000000000cccc<BR>
     * atomS[2] = x000000000000f0f0<BR>
     * atomS[3] = x000000000000ff00<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the complement of <i>zero</i>, of <i>one</i> and the table of
     * complements of the atomic propositions. The complements
     * are computed by means of code {@code fSps.complement(atom[i]); } and
     * stored within <i>fSps</i>;
     * </font><BR>
     * fSps.zero().complement();<BR>
     * System.out.println("complement(zero) = "+fSps.state());<BR>
     * fSps.one().complement();<BR>
     * System.out.println("complement(one) = "+fSps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fSps.complement(atomS[i]);<BR>
     * &nbsp; &nbsp; System.out.println("complement(atomS["+i+"]) = " +fSps.state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * complement(zero) = x000000000000fffe<BR>
     * complement(one) = x0000000000000000<BR>
     * complement(atomS[0]) = x0000000000005554<BR>
     * complement(atomS[1]) = x0000000000003332<BR>
     * complement(atomS[2]) = x0000000000000f0e<BR>
     * complement(atomS[3]) = x00000000000000fe<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the cocomplement of <i>zero</i>, of <i>one</i> and the table of
     * cocomplements of the atomic propositions. The cocomplements
     * are computed by means of code {@code fSps.cocomplement(atomS[i]); } and
     * stored within <i>fSps</i>;
     * </font><BR>
     * fSps.zero().cocomplement();<BR>
     * System.out.println("cocomplement(zero) = "+fSps.state());<BR>
     * fSps.one().cocomplement();<BR>
     * System.out.println("cocomplement(one) = "+fSps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fSps.cocomplement(atomS[i]);<BR>
     * &nbsp; &nbsp; System.out.println("cocomplement(atomS["+i+"]) = " +fSps.state());<BR>
     * }<BR>
     *
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * cocomplement(zero) = x000000000000fffe<BR>
     * cocomplement(one) = x0000000000000000<BR>
     * cocomplement(atomS[0]) = x0000000000005554<BR>
     * cocomplement(atomS[1]) = x0000000000003332<BR>
     * cocomplement(atomS[2]) = x0000000000000f0e<BR>
     * cocomplement(atomS[3]) = x00000000000000fe<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * The following codes (21 lines) tests the property <i>(A OR B) AND (B OR C) = B OR (A AND C )</i>,
     * and print the results of the test.
     * Propositions <i>A, B, C</i> are defined respectively as the <i>(theSizeOfFrame/4)</i>-th,
     *  <i>(theSizeOfFrame/2)</i>-th, <i>(3*theSizeOfFrame/4)</i>-th atomics propositions.
     * </font><BR>
     * System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: (A OR B) AND (B OR C) = B OR (A AND C )<BR>
     * </font>
     * finalSuperpowerset A = atomS[theSizeOfFrame/4].clone();
     * <font color="#0000FF" style="font-family: georgia">
     * // Method {@link Lattice#clone() } create an exact copy of the proposition</font><BR>
     * finalSuperpowerset B = atomS[theSizeOfFrame/2].clone();<BR>
     * finalSuperpowerset C = atomS[(3*theSizeOfFrame)/4].clone();<BR>
     * finalSuperpowerset AUB = fSps.instanceNsize();<BR>
     * finalSuperpowerset BUC = fSps.instanceNsize();<BR>
     * finalSuperpowerset ANC = fSps.instanceNsize();<BR>
     * finalSuperpowerset left = fSps.instanceNsize();<BR>
     * finalSuperpowerset right = fSps.instanceNsize();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A OR B</i> and store it within <i>AUB</i>;<BR>
     * Compute <i>B OR C</i> and store it within <i>BUC</i>;<BR>
     * </font>
     * AUB.or(A, B);<BR>
     * BUC.or(B, C);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A AND C</i> and store it within <i>ANC</i>;<BR>
     * Compute <i>AUB AND BUC</i> and store it within <i>left</i>;<BR>
     * Compute <i>B OR ANC</i> and store it within <i>right</i>;<BR>
     * </font>
     * ANC.and(A, C);<BR>
     * left.and(AUB,BUC);<BR>
     * right.or(B,ANC);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Test if <i>left</i> and <i>right</i> are equal, and store the result within
     * <i>isEqual</i>;<BR>
     * </font>
     * boolean isEqual=left.compareTo(right)==0;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print propositions <i>A,B,C,left,right</i> and the boolean <i>isEqual</i>;<BR>
     * </font>
     * System.out.println("A = atomS["+theSizeOfFrame/4+"] = " + A.state());<BR>
     * System.out.println("B = atomS["+theSizeOfFrame/2+"] = " + B.state());<BR>
     * System.out.println("C = atomS["+(3*theSizeOfFrame)/4+"] = " + C.state());<BR>
     * System.out.println("(A OR B) AND (B OR C)  = " + left.state());<BR>
     * System.out.println("B OR (A AND C ) = " + right.state());<BR>
     * System.out.println("isEqual = "+isEqual);<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * A = atomS[1] = x000000000000cccc<BR>
     * B = atomS[2] = x000000000000f0f0<BR>
     * C = atomS[3] = x000000000000ff00<BR>
     * (A OR B) AND (B OR C)  = x000000000000fcf0<BR>
     * B OR (A AND C ) = x000000000000fcf0<BR>
     * isEqual = true<BR>
     * </font>
     *
     */
    public void logical_tests_Superpowerset(int theSizeOfFrame) {

        int i;
        finalSuperpowerset fSps = new finalSuperpowerset();
        if(fSps.size(theSizeOfFrame)!=theSizeOfFrame) {
            System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Superpowerset(int)\n" +
                    "Exceed authorized bounds!");
            System.exit(0);
        }
        System.out.println("\n\n////// Test: Superpowerset - size = " + theSizeOfFrame +
                    "    //\n\n");
        finalSuperpowerset[] atomS = new finalSuperpowerset[theSizeOfFrame];
        for(i=0;i<theSizeOfFrame;i++) {
            atomS[i]=fSps.instanceNsize();
            atomS[i].atomic(i);
        }
        fSps.zero();
        System.out.println("zero = "+fSps.state());
        fSps.one();
        System.out.println("one = "+fSps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            System.out.println("atomS["+i+"] = " +atomS[i].state());
        }
        System.out.println("\n---------------------------------------\n");
        fSps.zero().complement();
        System.out.println("complement(zero) = "+fSps.state());
        fSps.one().complement();
        System.out.println("complement(one) = "+fSps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            fSps.complement(atomS[i]);
            System.out.println("complement(atomS["+i+"]) = " +fSps.state());
        }
        System.out.println("\n---------------------------------------\n");
        fSps.zero().cocomplement();
        System.out.println("cocomplement(zero) = "+fSps.state());
        fSps.one().cocomplement();
        System.out.println("cocomplement(one) = "+fSps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            fSps.cocomplement(atomS[i]);
            System.out.println("cocomplement(atomS["+i+"]) = " +fSps.state());
        }

        System.out.println("\n---------------------------------------\n");

        System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");
        finalSuperpowerset A = atomS[theSizeOfFrame/4].clone();
        finalSuperpowerset B = atomS[theSizeOfFrame/2].clone();
        finalSuperpowerset C = atomS[(3*theSizeOfFrame)/4].clone();
        finalSuperpowerset AUB = fSps.instanceNsize();
        finalSuperpowerset BUC = fSps.instanceNsize();
        finalSuperpowerset ANC = fSps.instanceNsize();
        finalSuperpowerset left = fSps.instanceNsize();
        finalSuperpowerset right = fSps.instanceNsize();
        AUB.or(A, B);
        BUC.or(B, C);
        ANC.and(A, C);
        left.and(AUB,BUC);
        right.or(B,ANC);
        boolean isEqual=left.compareTo(right)==0;
        System.out.println("A = atomS["+theSizeOfFrame/4+"] = " + A.state());
        System.out.println("B = atomS["+theSizeOfFrame/2+"] = " + B.state());
        System.out.println("C = atomS["+(3*theSizeOfFrame)/4+"] = " + C.state());
        System.out.println("(A OR B) AND (B OR C)  = " + left.state());
        System.out.println("B OR (A AND C ) = " + right.state());
        System.out.println("isEqual = "+isEqual);

    }

    /**
     * Test some logical manipulations on Openhyperpowerset.
     *
     * <BR><BR>
     * <b>Detailed code description.</b><BR>
     * The following typonomic conventions are used:<BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Blue color is used for commenting the following code.
     * </font><BR>
     * code formating is used for printing the code.<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * Red color is used for printing the possible output resulting of the
     * previous code.
     * </font>
     * </code><BR><BR>
     * For the output, it is assumed that <i>theSizeOfFrame=4</i> (parameter of the method).
     * <BR><BR>
     * Commented code:
     * <BR><BR>
     * <code>
     * int i;<BR>

     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of an open hyperpowerset, <i>fOhps</i>;
     * </font><BR>
     * finalOpenhyperpowerset fOhps = new finalOpenhyperpowerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Resize <i>fOhps</i> to <i>theSizeOfFrame</i>, the parameter of the method.
     * If the resizing failed, produce an error message and exit;
     * </font><BR>
     * if(fOhps.size(theSizeOfFrame)!=theSizeOfFrame) {<BR>
     * &nbsp; &nbsp; System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Openhyperpowerset(int)\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "Exceed authorized bounds!");<BR>
     * &nbsp; &nbsp; System.exit(0);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the title of the method;
     * </font><BR>
     * System.out.println("\n\n////// Test: Openhyperpowerset - size = " + theSizeOfFrame +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "    //\n\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: Openhyperpowerset - size = 4 &nbsp; &nbsp; //<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Create the table of atomic propositions, <i>atom</i>; <BR>
     * Create each atomic proposition by instancing and resizing from <i>fOhps</i>, using
     * {@code fOhps.instanceNsize();};<BR>
     * Define each atomic by applying the method {@link GeneratedLattice#atomic(int) }; <BR>
     * </font>
     * finalOpenhyperpowerset[] atom = new finalOpenhyperpowerset[theSizeOfFrame];<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; atom[i]=fOhps.instanceNsize();<BR>
     * &nbsp; &nbsp; atom[i].atomic(i);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print <i>zero</i>, <i>one</i> and the table of atomic propositions (printed
     * with hexadecimal coding);
     * </font><BR>
     * fOhps.zero();<BR>
     * System.out.println("zero = "+fOhps.state());<BR>
     * fOhps.one();<BR>
     * System.out.println("one = "+fOhps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; System.out.println("atom["+i+"] = " +atom[i].state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * zero = x0000000000000000<BR>
     * one = x000000000000ffff<BR>
     * atom[0] = x000000000000aaaa<BR>
     * atom[1] = x000000000000cccc<BR>
     * atom[2] = x000000000000f0f0<BR>
     * atom[3] = x000000000000ff00<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the complement of <i>zero</i>, of <i>one</i> and the table of
     * complements of the atomic propositions. The complements
     * are computed by means of code {@code fOhps.complement(atom[i]); } and
     * stored within <i>fOhps</i>;
     * </font><BR>
     * fOhps.zero().complement();<BR>
     * System.out.println("complement(zero) = "+fOhps.state());<BR>
     * fOhps.one().complement();<BR>
     * System.out.println("complement(one) = "+fOhps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fOhps.complement(atom[i]);<BR>
     * &nbsp; &nbsp; System.out.println("complement(atom["+i+"]) = " +fOhps.state());<BR>
     * }<BR>
     *
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * complement(zero) = x000000000000ffff<BR>
     * complement(one) = x0000000000000000<BR>
     * complement(atom[0]) = x0000000000000000<BR>
     * complement(atom[1]) = x0000000000000000<BR>
     * complement(atom[2]) = x0000000000000000<BR>
     * complement(atom[3]) = x0000000000000000<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the cocomplement of <i>zero</i>, of <i>one</i> and the table of
     * cocomplements of the atomic propositions. The cocomplements
     * are computed by means of code {@code fOhps.cocomplement(atom[i]); } and
     * stored within <i>fOhps</i>;
     * </font><BR>
     * fOhps.zero().cocomplement();<BR>
     * System.out.println("cocomplement(zero) = "+fOhps.state());<BR>
     * fOhps.one().cocomplement();<BR>
     * System.out.println("cocomplement(one) = "+fOhps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fOhps.cocomplement(atom[i]);<BR>
     * &nbsp; &nbsp; System.out.println("cocomplement(atom["+i+"]) = " +fOhps.state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * cocomplement(zero) = x000000000000ffff<BR>
     * cocomplement(one) = x0000000000000000<BR>
     * cocomplement(atom[0]) = x000000000000ffff<BR>
     * cocomplement(atom[1]) = x000000000000ffff<BR>
     * cocomplement(atom[2]) = x000000000000ffff<BR>
     * cocomplement(atom[3]) = x000000000000ffff<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * The following codes (21 lines) tests the property <i>(A OR B) AND (B OR C) = B OR (A AND C )</i>,
     * and print the results of the test.
     * Propositions <i>A, B, C</i> are defined respectively as the <i>(theSizeOfFrame/4)</i>-th,
     *  <i>(theSizeOfFrame/2)</i>-th, <i>(3*theSizeOfFrame/4)</i>-th atomics propositions.
     * </font><BR>
     * System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: (A OR B) AND (B OR C) = B OR (A AND C )<BR>
     * </font>
     * finalOpenhyperpowerset A = atom[theSizeOfFrame/4].clone();
     * <font color="#0000FF" style="font-family: georgia">
     * // Method {@link Lattice#clone() } create an exact copy of the proposition</font><BR>
     * finalOpenhyperpowerset B = atom[theSizeOfFrame/2].clone();<BR>
     * finalOpenhyperpowerset C = atom[(3*theSizeOfFrame)/4].clone();<BR>
     * finalOpenhyperpowerset AUB = fOhps.instanceNsize();<BR>
     * finalOpenhyperpowerset BUC = fOhps.instanceNsize();<BR>
     * finalOpenhyperpowerset ANC = fOhps.instanceNsize();<BR>
     * finalOpenhyperpowerset left = fOhps.instanceNsize();<BR>
     * finalOpenhyperpowerset right = fOhps.instanceNsize();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A OR B</i> and store it within <i>AUB</i>;<BR>
     * Compute <i>B OR C</i> and store it within <i>BUC</i>;<BR>
     * </font>
     * AUB.or(A, B);<BR>
     * BUC.or(B, C);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A AND C</i> and store it within <i>ANC</i>;<BR>
     * Compute <i>AUB AND BUC</i> and store it within <i>left</i>;<BR>
     * Compute <i>B OR ANC</i> and store it within <i>right</i>;<BR>
     * </font>
     * ANC.and(A, C);<BR>
     * left.and(AUB,BUC);<BR>
     * right.or(B,ANC);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Test if <i>left</i> and <i>right</i> are equal, and store the result within
     * <i>isEqual</i>;<BR>
     * </font>
     * boolean isEqual=left.compareTo(right)==0;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print propositions <i>A,B,C,left,right</i> and the boolean <i>isEqual</i>;<BR>
     * </font>
     * System.out.println("A = atom["+theSizeOfFrame/4+"] = " + A.state());<BR>
     * System.out.println("B = atom["+theSizeOfFrame/2+"] = " + B.state());<BR>
     * System.out.println("C = atom["+(3*theSizeOfFrame)/4+"] = " + C.state());<BR>
     * System.out.println("(A OR B) AND (B OR C)  = " + left.state());<BR>
     * System.out.println("B OR (A AND C ) = " + right.state());<BR>
     * System.out.println("isEqual = "+isEqual);<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * A = atom[1] = x000000000000cccc<BR>
     * B = atom[2] = x000000000000f0f0<BR>
     * C = atom[3] = x000000000000ff00<BR>
     * (A OR B) AND (B OR C)  = x000000000000fcf0<BR>
     * B OR (A AND C ) = x000000000000fcf0<BR>
     * isEqual = true<BR>
     * </font>
     * </code>
     *
     */
    public void logical_tests_Openhyperpowerset(int theSizeOfFrame) {

        int i;
        finalOpenhyperpowerset fOhps = new finalOpenhyperpowerset();
        if(fOhps.size(theSizeOfFrame)!=theSizeOfFrame) {
            System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Openhyperpowerset(int)\n" +
                    "Exceed authorized bounds!");
            System.exit(0);
        }
        System.out.println("\n\n////// Test: Openhyperpowerset - size = " + theSizeOfFrame +
                    "    //\n\n");
        finalOpenhyperpowerset[] atom = new finalOpenhyperpowerset[theSizeOfFrame];
        for(i=0;i<theSizeOfFrame;i++) {
            atom[i]=fOhps.instanceNsize();
            atom[i].atomic(i);
        }
        fOhps.zero();
        System.out.println("zero = "+fOhps.state());
        fOhps.one();
        System.out.println("one = "+fOhps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            System.out.println("atom["+i+"] = " +atom[i].state());
        }
        System.out.println("\n---------------------------------------\n");
        fOhps.zero().complement();
        System.out.println("complement(zero) = "+fOhps.state());
        fOhps.one().complement();
        System.out.println("complement(one) = "+fOhps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            fOhps.complement(atom[i]);
            System.out.println("complement(atom["+i+"]) = " +fOhps.state());
        }
        System.out.println("\n---------------------------------------\n");
        fOhps.zero().cocomplement();
        System.out.println("cocomplement(zero) = "+fOhps.state());
        fOhps.one().cocomplement();
        System.out.println("cocomplement(one) = "+fOhps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            fOhps.cocomplement(atom[i]);
            System.out.println("cocomplement(atom["+i+"]) = " +fOhps.state());
        }
        System.out.println("\n---------------------------------------\n");

        System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");
        finalOpenhyperpowerset A = atom[theSizeOfFrame/4].clone();
        finalOpenhyperpowerset B = atom[theSizeOfFrame/2].clone();
        finalOpenhyperpowerset C = atom[(3*theSizeOfFrame)/4].clone();
        finalOpenhyperpowerset AUB = fOhps.instanceNsize();
        finalOpenhyperpowerset BUC = fOhps.instanceNsize();
        finalOpenhyperpowerset ANC = fOhps.instanceNsize();
        finalOpenhyperpowerset left = fOhps.instanceNsize();
        finalOpenhyperpowerset right = fOhps.instanceNsize();
        AUB.or(A, B);
        BUC.or(B, C);
        ANC.and(A, C);
        left.and(AUB,BUC);
        right.or(B,ANC);
        boolean isEqual=left.compareTo(right)==0;
        System.out.println("A = atom["+theSizeOfFrame/4+"] = " + A.state());
        System.out.println("B = atom["+theSizeOfFrame/2+"] = " + B.state());
        System.out.println("C = atom["+(3*theSizeOfFrame)/4+"] = " + C.state());
        System.out.println("(A OR B) AND (B OR C)  = " + left.state());
        System.out.println("B OR (A AND C ) = " + right.state());
        System.out.println("isEqual = "+isEqual);

    }

    /**
     * Test some logical manipulations on Closedhyperpowerset.
     *
     * <BR><BR>
     * <b>Detailed code description.</b><BR>
     * The following typonomic conventions are used:<BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Blue color is used for commenting the following code.
     * </font><BR>
     * code formating is used for printing the code.<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * Red color is used for printing the possible output resulting of the
     * previous code.
     * </font>
     * </code><BR><BR>
     * For the output, it is assumed that <i>theSizeOfFrame=4</i> (parameter of the method).
     * <BR><BR>
     * Commented code:
     * <BR><BR>
     * <code>
     * int i;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of a closed hyperpowerset, <i>fChps</i>;
     * </font><BR>
     * finalClosedhyperpowerset fChps = new finalClosedhyperpowerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Resize <i>fChps</i> to <i>theSizeOfFrame</i>, the parameter of the method.
     * If the resizing failed, produce an error message and exit;
     * </font><BR>
     * if(fChps.size(theSizeOfFrame)!=theSizeOfFrame) {<BR>
     * &nbsp; &nbsp; System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Closedhyperpowerset(int)\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "Exceed authorized bounds!");<BR>
     * &nbsp; &nbsp; System.exit(0);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the title of the method;
     * </font><BR>
     * System.out.println("\n\n////// Test: Closedhyperpowerset - size = " + theSizeOfFrame +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "    //\n\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: Closedhyperpowerset - size = 4 &nbsp; &nbsp; //<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Create the table of atomic propositions, <i>atomC</i>; <BR>
     * Create each atomic proposition by instancing and resizing from <i>fChps</i>, using
     * {@code fChps.instanceNsize();};<BR>
     * Define each atomic by applying the method {@link GeneratedLattice#atomic(int) }; <BR>
     * </font>
     * finalClosedhyperpowerset[] atomC = new finalClosedhyperpowerset[theSizeOfFrame];<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; atomC[i]=fChps.instanceNsize();<BR>
     * &nbsp; &nbsp; atomC[i].atomic(i);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print <i>zero</i>, <i>one</i> and the table of atomic propositions (printed
     * with hexadecimal coding);
     * </font><BR>
     * fChps.zero();<BR>
     * System.out.println("zero = "+fChps.state());<BR>
     * fChps.one();<BR>
     * System.out.println("one = "+fChps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; System.out.println("atomC["+i+"] = " +atomC[i].state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * zero = x0000000000000000 <BR>
     * one = x000000000000fffe <BR>
     * atomC[0] = x000000000000aaaa <BR>
     * atomC[1] = x000000000000cccc <BR>
     * atomC[2] = x000000000000f0f0 <BR>
     * atomC[3] = x000000000000ff00 <BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * Print the complement of <i>zero</i>, of <i>one</i> and the table of complements
     * of the atomic propositions. The complements are computed by means of code
     * {@code fChps.complement(atomC[i]); } and stored within <i>fChps</i>;
     * </font><BR>
     * fChps.zero().complement();<BR>
     * System.out.println("complement(zero) = "+fChps.state());<BR>
     * fChps.one().complement();<BR>
     * System.out.println("complement(one) = "+fChps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fChps.complement(atomC[i]);<BR>
     * &nbsp; &nbsp; System.out.println("complement(atomC["+i+"]) = " +fChps.state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * complement(zero) = x000000000000fffe<BR>
     * complement(one) = x0000000000000000<BR>
     * complement(atomC[0]) = x0000000000000000<BR>
     * complement(atomC[1]) = x0000000000000000<BR>
     * complement(atomC[2]) = x0000000000000000<BR>
     * complement(atomC[3]) = x0000000000000000<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * 
     * <font color="#0000FF" style="font-family: georgia">
     * Print the cocomplement of <i>zero</i>, of <i>one</i> and the table of cocomplements
     * of the atomic propositions. The cocomplements
     * are computed by means of code {@code fChps.cocomplement(atomC[i]); } and
     * stored within <i>fChps</i>;
     * </font><BR>
     * fChps.zero().cocomplement();<BR>
     * System.out.println("cocomplement(zero) = "+fChps.state());<BR>
     * fChps.one().cocomplement();<BR>
     * System.out.println("cocomplement(one) = "+fChps.state());<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fChps.cocomplement(atomC[i]);<BR>
     * &nbsp; &nbsp; System.out.println("cocomplement(atomC["+i+"]) = " +fChps.state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * cocomplement(zero) = x000000000000fffe<BR>
     * cocomplement(one) = x0000000000000000<BR>
     * cocomplement(atomC[0]) = x000000000000fffc<BR>
     * cocomplement(atomC[1]) = x000000000000fffa<BR>
     * cocomplement(atomC[2]) = x000000000000ffee<BR>
     * cocomplement(atomC[3]) = x000000000000fefe<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * The following codes (21 lines) tests the property <i>(A OR B) AND (B OR C) = B OR (A AND C )</i>,
     * and print the results of the test.
     * Propositions <i>A, B, C</i> are defined respectively as the <i>(theSizeOfFrame/4)</i>-th,
     *  <i>(theSizeOfFrame/2)</i>-th, <i>(3*theSizeOfFrame/4)</i>-th atomics propositions.
     * </font><BR>
     * System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: (A OR B) AND (B OR C) = B OR (A AND C )<BR>
     * </font>
     * finalClosedhyperpowerset A = atomC[theSizeOfFrame/4].clone();
     * <font color="#0000FF" style="font-family: georgia">
     * // Method {@link Lattice#clone() } create an exact copy of the proposition</font><BR>
     * finalClosedhyperpowerset B = atomC[theSizeOfFrame/2].clone();<BR>
     * finalClosedhyperpowerset C = atomC[(3*theSizeOfFrame)/4].clone();<BR>
     * finalClosedhyperpowerset AUB = fChps.instanceNsize();<BR>
     * finalClosedhyperpowerset BUC = fChps.instanceNsize();<BR>
     * finalClosedhyperpowerset ANC = fChps.instanceNsize();<BR>
     * finalClosedhyperpowerset left = fChps.instanceNsize();<BR>
     * finalClosedhyperpowerset right = fChps.instanceNsize();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A OR B</i> and store it within <i>AUB</i>;<BR>
     * Compute <i>B OR C</i> and store it within <i>BUC</i>;<BR>
     * </font>
     * AUB.or(A, B);<BR>
     * BUC.or(B, C);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A AND C</i> and store it within <i>ANC</i>;<BR>
     * Compute <i>AUB AND BUC</i> and store it within <i>left</i>;<BR>
     * Compute <i>B OR ANC</i> and store it within <i>right</i>;<BR>
     * </font>
     * ANC.and(A, C);<BR>
     * left.and(AUB,BUC);<BR>
     * right.or(B,ANC);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Test if <i>left</i> and <i>right</i> are equal, and store the result within
     * <i>isEqual</i>;<BR>
     * </font>
     * boolean isEqual=left.compareTo(right)==0;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print propositions <i>A,B,C,left,right</i> and the boolean <i>isEqual</i>;<BR>
     * </font>
     * System.out.println("A = atomC["+theSizeOfFrame/4+"] = " + A.state());<BR>
     * System.out.println("B = atomC["+theSizeOfFrame/2+"] = " + B.state());<BR>
     * System.out.println("C = atomC["+(3*theSizeOfFrame)/4+"] = " + C.state());<BR>
     * System.out.println("(A OR B) AND (B OR C)  = " + left.state());<BR>
     * System.out.println("B OR (A AND C ) = " + right.state());<BR>
     * System.out.println("isEqual = "+isEqual);<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * A = atomC[1] = x000000000000cccc<BR>
     * B = atomC[2] = x000000000000f0f0<BR>
     * C = atomC[3] = x000000000000ff00<BR>
     * (A OR B) AND (B OR C)  = x000000000000fcf0<BR>
     * B OR (A AND C ) = x000000000000fcf0<BR>
     * isEqual = true<BR>
     * </font>
     * </code>
     *
     */
    public void logical_tests_Closedhyperpowerset(int theSizeOfFrame) {

        int i;
        finalClosedhyperpowerset fChps = new finalClosedhyperpowerset();
        if(fChps.size(theSizeOfFrame)!=theSizeOfFrame) {
            System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Closedhyperpowerset(int)\n" +
                    "Exceed authorized bounds!");
            System.exit(0);
        }
        System.out.println("\n\n////// Test: Closedhyperpowerset - size = " + theSizeOfFrame +
                    "    //\n\n");
        finalClosedhyperpowerset[] atomC = new finalClosedhyperpowerset[theSizeOfFrame];
        for(i=0;i<theSizeOfFrame;i++) {
            atomC[i]=fChps.instanceNsize();
            atomC[i].atomic(i);
        }
        fChps.zero();
        System.out.println("zero = "+fChps.state());
        fChps.one();
        System.out.println("one = "+fChps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            System.out.println("atomC["+i+"] = " +atomC[i].state());
        }
        System.out.println("\n---------------------------------------\n");
        fChps.zero().complement();
        System.out.println("complement(zero) = "+fChps.state());
        fChps.one().complement();
        System.out.println("complement(one) = "+fChps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            fChps.complement(atomC[i]);
            System.out.println("complement(atomC["+i+"]) = " +fChps.state());
        }
        System.out.println("\n---------------------------------------\n");
        fChps.zero().cocomplement();
        System.out.println("cocomplement(zero) = "+fChps.state());
        fChps.one().cocomplement();
        System.out.println("cocomplement(one) = "+fChps.state());
        for(i=0;i<theSizeOfFrame;i++) {
            fChps.cocomplement(atomC[i]);
            System.out.println("cocomplement(atomC["+i+"]) = " +fChps.state());
        }

        System.out.println("\n---------------------------------------\n");

        System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");
        finalClosedhyperpowerset A = atomC[theSizeOfFrame/4].clone();
        finalClosedhyperpowerset B = atomC[theSizeOfFrame/2].clone();
        finalClosedhyperpowerset C = atomC[(3*theSizeOfFrame)/4].clone();
        finalClosedhyperpowerset AUB = fChps.instanceNsize();
        finalClosedhyperpowerset BUC = fChps.instanceNsize();
        finalClosedhyperpowerset ANC = fChps.instanceNsize();
        finalClosedhyperpowerset left = fChps.instanceNsize();
        finalClosedhyperpowerset right = fChps.instanceNsize();
        AUB.or(A, B);
        BUC.or(B, C);
        ANC.and(A, C);
        left.and(AUB,BUC);
        right.or(B,ANC);
        boolean isEqual=left.compareTo(right)==0;
        System.out.println("A = atomC["+theSizeOfFrame/4+"] = " + A.state());
        System.out.println("B = atomC["+theSizeOfFrame/2+"] = " + B.state());
        System.out.println("C = atomC["+(3*theSizeOfFrame)/4+"] = " + C.state());
        System.out.println("(A OR B) AND (B OR C)  = " + left.state());
        System.out.println("B OR (A AND C ) = " + right.state());
        System.out.println("isEqual = "+isEqual);

    }

    /**
     * Test some logical manipulations on Powerset.
     *
     * <BR><BR>
     * <b>Detailed code description.</b><BR>
     * The following typonomic conventions are used:<BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Blue color is used for commenting the following code.
     * </font><BR>
     * code formating is used for printing the code.<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * Red color is used for printing the possible output resulting of the
     * previous code.
     * </font>
     * </code><BR><BR>
     * For the output, it is assumed that <i>theSizeOfFrame=8</i> (parameter of the method).
     * <BR><BR>
     * Commented code:
     * <BR><BR>
     * <code>
     * int i;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of a powerset, <i>fPset</i>;
     * </font><BR>
     * finalPowerset fPset = new finalPowerset();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Resize <i>fPset</i> to <i>theSizeOfFrame</i>, the parameter of the method.
     * If the resizing failed, produce an error message and exit;
     * </font><BR>
     * if(fPset.size(theSizeOfFrame)!=theSizeOfFrame) {<BR>
     * &nbsp; &nbsp; System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Closedhyperpowerset(int)\n" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "Exceed authorized bounds!");<BR>
     * &nbsp; &nbsp; System.exit(0);<BR>
     * }<BR>
     * //<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the title of the method;
     * </font><BR>
     * System.out.println("\n\n////// Test: Powerset - size = " + theSizeOfFrame +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; "    //\n\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: Powerset - size = 8 &nbsp; &nbsp; //<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Create the table of atomic propositions, <i>atomPset</i>; <BR>
     * Create each atomic proposition by instancing and resizing from <i>fPset</i>, using
     * {@code fPset.instanceNsize();};<BR>
     * Define each atomic by applying the method {@link GeneratedLattice#atomic(int) }; <BR>
     * </font>
     * finalPowerset[] atomPset = new finalPowerset[theSizeOfFrame];<BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; atomPset[i]=fPset.instanceNsize();<BR>
     * &nbsp; &nbsp; atomPset[i].atomic(i);<BR>
     * }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the table of atomic propositions (printed with hexadecimal coding);
     * </font><BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; System.out.println("atomPset["+i+"]" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; " = " +atomPset[i].state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * atomPset[0] = x0000000000000001<BR>
     * atomPset[1] = x0000000000000002<BR>
     * atomPset[2] = x0000000000000004<BR>
     * atomPset[3] = x0000000000000008<BR>
     * atomPset[4] = x0000000000000010<BR>
     * atomPset[5] = x0000000000000020<BR>
     * atomPset[6] = x0000000000000040<BR>
     * atomPset[7] = x0000000000000080<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the table of complements of the atomic propositions. The complements
     * are computed by means of code {@code fPset.complement(atomPset[i]); } and
     * stored within <i>fPset</i>;
     * </font><BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) } {<BR>
     * &nbsp; &nbsp; fPset.complement(atomPset[i]);<BR>
     * &nbsp; &nbsp; System.out.println("complement(atomPset["+i+"])" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; " = " +fPset.state());<BR>
     * }<BR>
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * complement(atomPset[0]) = x00000000000000fe<BR>
     * complement(atomPset[1]) = x00000000000000fd<BR>
     * complement(atomPset[2]) = x00000000000000fb<BR>
     * complement(atomPset[3]) = x00000000000000f7<BR>
     * complement(atomPset[4]) = x00000000000000ef<BR>
     * complement(atomPset[5]) = x00000000000000df<BR>
     * complement(atomPset[6]) = x00000000000000bf<BR>
     * complement(atomPset[7]) = x000000000000007f<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     * <font color="#0000FF" style="font-family: georgia">
     * Print the table of cocomplements of the atomic propositions. The cocomplements
     * are computed by means of code {@code fPset.cocomplement(atomPset[i]); } and
     * stored within <i>fPset</i>;
     * </font><BR>
     * {@code for(i=0;i<theSizeOfFrame;i++) }{<BR>
     * &nbsp; &nbsp; fPset.cocomplement(atomPset[i]);<BR>
     * &nbsp; &nbsp; System.out.println("cocomplement(atomPset["+i+"])" +<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; " = " +fPset.state());<BR>
     * }<BR>
     *
     * System.out.println("\n---------------------------------------\n");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * cocomplement(atomPset[0]) = x00000000000000fe<BR>
     * cocomplement(atomPset[1]) = x00000000000000fd<BR>
     * cocomplement(atomPset[2]) = x00000000000000fb<BR>
     * cocomplement(atomPset[3]) = x00000000000000f7<BR>
     * cocomplement(atomPset[4]) = x00000000000000ef<BR>
     * cocomplement(atomPset[5]) = x00000000000000df<BR>
     * cocomplement(atomPset[6]) = x00000000000000bf<BR>
     * cocomplement(atomPset[7]) = x000000000000007f<BR>
     * <BR>
     * ---------------------------------------<BR>
     * </font>
     *
     * <font color="#0000FF" style="font-family: georgia">
     * The following codes (21 lines) tests the property <i>(A OR B) AND (B OR C) = B OR (A AND C )</i>,
     * and print the results of the test.
     * Propositions <i>A, B, C</i> are defined respectively as the <i>(theSizeOfFrame/4)</i>-th,
     *  <i>(theSizeOfFrame/2)</i>-th, <i>(3*theSizeOfFrame/4)</i>-th atomics propositions.
     * </font><BR>
     * System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * ////// Test: (A OR B) AND (B OR C) = B OR (A AND C )<BR>
     * </font>
     * finalPowerset A = atomPset[theSizeOfFrame/4].clone(); <font color="#0000FF" style="font-family: georgia">
     * // Method {@link Lattice#clone() } create an exact copy of the proposition</font><BR>
     * finalPowerset B = atomPset[theSizeOfFrame/2].clone();<BR>
     * finalPowerset C = atomPset[(3*theSizeOfFrame)/4].clone();<BR>
     * finalPowerset AUB = fPset.instanceNsize();<BR>
     * finalPowerset BUC = fPset.instanceNsize();<BR>
     * finalPowerset ANC = fPset.instanceNsize();<BR>
     * finalPowerset left = fPset.instanceNsize();<BR>
     * finalPowerset right = fPset.instanceNsize();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A OR B</i> and store it within <i>AUB</i>;<BR>
     * Compute <i>B OR C</i> and store it within <i>BUC</i>;<BR>
     * </font>
     * AUB.or(A, B);<BR>
     * BUC.or(B, C);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Compute <i>A AND C</i> and store it within <i>ANC</i>;<BR>
     * Compute <i>AUB AND BUC</i> and store it within <i>left</i>;<BR>
     * Compute <i>B OR ANC</i> and store it within <i>right</i>;<BR>
     * </font>
     * ANC.and(A, C);<BR>
     * left.and(AUB,BUC);<BR>
     * right.or(B,ANC);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Test if <i>left</i> and <i>right</i> are equal, and store the result within
     * <i>isEqual</i>;<BR>
     * </font>
     * boolean isEqual=left.compareTo(right)==0;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Print propositions <i>A,B,C,left,right</i> and the boolean <i>isEqual</i>;<BR>
     * </font>
     * System.out.println("A = atomPset["+theSizeOfFrame/4+"] = " + A.state());<BR>
     * System.out.println("B = atomPset["+theSizeOfFrame/2+"] = " + B.state());<BR>
     * System.out.println("C = atomPset["+(3*theSizeOfFrame)/4+"] = " + C.state());<BR>
     * System.out.println("(A OR B) AND (B OR C)  = " + left.state());<BR>
     * System.out.println("B OR (A AND C ) = " + right.state());<BR>
     * System.out.println("isEqual = "+isEqual);<BR>
     * <font color="#FF0000" style="font-family: georgia">
     * A = atomPset[2] = x0000000000000004<BR>
     * B = atomPset[4] = x0000000000000010<BR>
     * C = atomPset[6] = x0000000000000040<BR>
     * (A OR B) AND (B OR C)  = x0000000000000010<BR>
     * B OR (A AND C ) = x0000000000000010<BR>
     * isEqual = true<BR>
     * </font>
     *
     * </code>
     *
     */
    public void logical_tests_Powerset(int theSizeOfFrame) {

        int i;
        finalPowerset fPset = new finalPowerset();
        if(fPset.size(theSizeOfFrame)!=theSizeOfFrame) {
            System.err.println("Error :: RefereeToolbox_Tutorial.logical_tests_Closedhyperpowerset(int)\n" +
                    "Exceed authorized bounds!");
            System.exit(0);
        }
        //
        System.out.println("\n\n////// Test: Powerset - size = " + theSizeOfFrame +
                    "    //\n\n");
        finalPowerset[] atomPset = new finalPowerset[theSizeOfFrame];
        for(i=0;i<theSizeOfFrame;i++) {
                atomPset[i]=fPset.instanceNsize();
                atomPset[i].atomic(i);
        }
        for(i=0;i<theSizeOfFrame;i++) {
            System.out.println("atomPset["+i+"]" +
                " = " +atomPset[i].state());
        }
        System.out.println("\n---------------------------------------\n");
        for(i=0;i<theSizeOfFrame;i++) {
            fPset.complement(atomPset[i]);
            System.out.println("complement(atomPset["+i+"])" +
                " = " +fPset.state());
        }
        System.out.println("\n---------------------------------------\n");
        for(i=0;i<theSizeOfFrame;i++) {
            fPset.cocomplement(atomPset[i]);
            System.out.println("cocomplement(atomPset["+i+"])" +
                " = " +fPset.state());
        }

        System.out.println("\n---------------------------------------\n");

        System.out.println("\n\n////// Test: (A OR B) AND (B OR C) = B OR (A AND C )");
        finalPowerset A = atomPset[theSizeOfFrame/4].clone();
        finalPowerset B = atomPset[theSizeOfFrame/2].clone();
        finalPowerset C = atomPset[(3*theSizeOfFrame)/4].clone();
        finalPowerset AUB = fPset.instanceNsize();
        finalPowerset BUC = fPset.instanceNsize();
        finalPowerset ANC = fPset.instanceNsize();
        finalPowerset left = fPset.instanceNsize();
        finalPowerset right = fPset.instanceNsize();
        AUB.or(A, B);
        BUC.or(B, C);
        ANC.and(A, C);
        left.and(AUB,BUC);
        right.or(B,ANC);
        boolean isEqual=left.compareTo(right)==0;
        System.out.println("A = atomPset["+theSizeOfFrame/4+"] = " + A.state());
        System.out.println("B = atomPset["+theSizeOfFrame/2+"] = " + B.state());
        System.out.println("C = atomPset["+(3*theSizeOfFrame)/4+"] = " + C.state());
        System.out.println("(A OR B) AND (B OR C)  = " + left.state());
        System.out.println("B OR (A AND C ) = " + right.state());
        System.out.println("isEqual = "+isEqual);

    }

    /**
     * Compare different referee functions applied to the fusion of 3 bba.
     * Both sampling and relaxed approaches are considered.
     *
     * This example works like {@link RefereeToolbox_Tutorial#RefereeFuserRTS_Comparison() }
     * and {@link RefereeToolbox_Tutorial#RefereeSampler_Comparison() } so that
     * it is not explained in details.
     * However, it is noticed that the combinations work here on three sources,
     * denoted <i>aFuser1</i>,  <i>aFuser2</i>,  <i>aFuser3</i> for the relaxed approach,
     * and <i>aSampler1</i>,  <i>aSampler2</i>,  <i>aSampler3</i> for the sampled approach.
     * In order to compute on these tree sources, it is necessary to use an <i>ArrayList</i>
     * containing the sources, and apply the methods:<BR>
     * {@link BBARefereeFuser#fuse(java.util.ArrayList, RefereeToolbox.RefereeFunctionDefault) }
     * for the relaxed approach<BR>
     * {@link SampledBBARefereeFuser#setFuser(java.util.ArrayList, RefereeToolbox.RefereeFunctionDefault) }
     * for the sampling approach<BR>
     * <BR>
     * Typically, the fusion by means of PCR# is done by the following codes:<BR>
     * <code>
     * // referee function definition<BR>
     * RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();<BR>
     * // [...]<BR>
     * // Array creation<BR>
     * <font color="#FF0000" >
     * ArrayList<finalRefereeFuserRTS_Powerset> bbaArrayR =<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; new {@code ArrayList<finalRefereeFuserRTS_Powerset>}();<BR>
     * bbaArrayR.add(aFuser1);<BR>
     * bbaArrayR.add(aFuser2);<BR>
     * bbaArrayR.add(aFuser3);<BR>
     * </font>
     * // [...]<BR>
     * // Combination<BR>
     * <font color="#FF0000" >
     * aFuser.fuse(bbaArrayR,referee5);<BR>
     * </font>
     * </code>
     * for the relaxed approach,<BR>
     * <code>
     * // referee function definition<BR>
     * RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();<BR>
     * // [...]<BR>
     * // Array creation<BR>
     * <font color="#FF0000" >
     * ArrayList<finalRefereeSampler_Powerset> bbaArrayS =<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; new {@code ArrayList<finalRefereeSampler_Powerset>}();<BR>
     * bbaArrayS.add(aSampler1);<BR>
     * bbaArrayS.add(aSampler2);<BR>
     * bbaArrayS.add(aSampler3);<BR>
     * </font>
     * // [...]<BR>
     * // Combination<BR>
     * <font color="#FF0000" >
     * aSampler.setFuser(bbaArrayS, referee5);<BR>
     * </font>
     * Samples.clear();<BR>
     * {@code for(n=0;n<NbSamples;n++) } Samples.add(aSampler.makeFusedSample());<BR>
     * Z=aSampler.learnFrom(Samples.toArray());<BR>
     * </code>
     * for the sampled approach,<BR>
     *<BR>
     * where <i>aFuser</i> and <i>aSampler</i> are output bba for each
     * respective approaches.
     *
     */
    public void Referee_On_3_Entries(){

        int printMode=1;
        //
        finalPowerset A=new finalPowerset();
        A.size(3); A.atomic(0);
        finalPowerset B=A.instanceNsize(); B.atomic(1);
        finalPowerset C=A.instanceNsize(); C.atomic(2);
        //
        finalPowerset AUB=A.instanceNsize(); AUB.or(A,B);
        finalPowerset BUC=A.instanceNsize(); BUC.or(B,C);
        finalPowerset CUA=A.instanceNsize(); CUA.or(C,A);
        //
        finalPowerset zero=A.instanceNsize(); zero.zero();
        finalPowerset one=A.instanceNsize(); one.one();
        //
        //
        RFDempster_Powerset referee1 = new RFDempster_Powerset();
        RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();
        RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();
        RFPCR6_Powerset referee4 = new RFPCR6_Powerset();
        RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();
        //
        //
        finalRefereeFuserRTS_Powerset aFuser1 = new finalRefereeFuserRTS_Powerset();
        aFuser1.add(A,0.6);
        aFuser1.add(AUB,0.4);
        //
        finalRefereeFuserRTS_Powerset aFuser2 = aFuser1.instance();
        aFuser2.add(A,0.3);
        aFuser2.add(CUA,0.7);
        //
        finalRefereeFuserRTS_Powerset aFuser3 = aFuser1.instance();
        aFuser3.add(B,0.8);
        aFuser3.add(one,0.2);
        //
        ArrayList<finalRefereeFuserRTS_Powerset> bbaArrayR =
                new ArrayList<finalRefereeFuserRTS_Powerset>();
        bbaArrayR.add(aFuser1);
        bbaArrayR.add(aFuser2);
        bbaArrayR.add(aFuser3);
        //
        finalRefereeFuserRTS_Powerset aFuser = aFuser1.instance();

        finalRefereeSampler_Powerset aSampler1 = new finalRefereeSampler_Powerset();
        aSampler1.addAll(aFuser1.toArray());
        //
        finalRefereeSampler_Powerset aSampler2 = aSampler1.instance();
        aSampler2.addAll(aFuser2.toArray());
        //
        finalRefereeSampler_Powerset aSampler3 = aSampler1.instance();
        aSampler3.addAll(aFuser3.toArray());
        //
        ArrayList<finalRefereeSampler_Powerset> bbaArrayS =
                new ArrayList<finalRefereeSampler_Powerset>();
        bbaArrayS.add(aSampler1);
        bbaArrayS.add(aSampler2);
        bbaArrayS.add(aSampler3);
        //
        finalRefereeSampler_Powerset aSampler = aSampler1.instance();
        //
        //
        System.out.println(
                "/////////////////////////////\n" +
                "// Relaxed Method\n" +
                "////////////////////\n");
        System.out.println("aFuser1");
            System.out.println(aFuser1.state(printMode));
            System.out.println();
        System.out.println("aFuser2");
            System.out.println(aFuser2.state(printMode));
            System.out.println();
        System.out.println("aFuser3");
            System.out.println(aFuser3.state(printMode));
            System.out.println();
        aFuser.fuse(bbaArrayR,referee1);
        System.out.println("aFuser - Dempster");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();
        aFuser.fuse(bbaArrayR,referee2);
        System.out.println("aFuser - Disjunctive");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();
        aFuser.fuse(bbaArrayR,referee3);
        System.out.println("aFuser - Dubois & Prade");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();
        aFuser.fuse(bbaArrayR,referee4);
        System.out.println("aFuser - PCR6");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();
        aFuser.fuse(bbaArrayR,referee5);
        System.out.println("aFuser - PCR#");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();

        System.out.println(
                "/////////////////////////////\n" +
                "// Sampling Method\n" +
                "////////////////////\n");

        int n;
        int NbSamples=1000000;
        double Z;
        finalRefereeSampler_Powerset Samples= aSampler1.instance();

        System.out.println("aSampler1");
            System.out.println(aSampler1.state(printMode));
            System.out.println();
        System.out.println("aSampler2");
            System.out.println(aSampler2.state(printMode));
            System.out.println();
        System.out.println("aSampler3");
            System.out.println(aSampler3.state(printMode));
            System.out.println();
        //
        aSampler.setFuser(bbaArrayS, referee1);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - Dempster -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();
            //
        aSampler.setFuser(bbaArrayS, referee2);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - Disjunctive -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();
            //
        aSampler.setFuser(bbaArrayS, referee3);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - Dubois & Prade -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();
            //
        aSampler.setFuser(bbaArrayS, referee4);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - PCR6 -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();
            //
        aSampler.setFuser(bbaArrayS, referee5);
        Samples.clear();
        for(n=0;n<NbSamples;n++) Samples.add(aSampler.makeFusedSample());
        Z=aSampler.learnFrom(Samples.toArray());
        System.out.println("aSampler - PCR# -- " + NbSamples + " particles");
            System.out.println("Conflict Z = "+Z+" %");
            System.out.println(aSampler.state(printMode));
            System.out.println();

    }


    /**
     * Demonstrate the creation of a new Referee Function.
     * <BR><BR>
     * In this example, a new referee function is created from the referee function
     * for Dempster-Shafer by excluding a proposition <i>excludedProposition</i>.
     * More precisely:<BR>
     * &nbsp; &nbsp; - If <i>excludedProposition=null</i>, just do as for Dempster-Shafer,
     * <BR>
     * &nbsp; &nbsp; - Otherwise:<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; - Let <i>(X , 1.)</i> be the referee output for Dempster-Shafer,<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; - If <i>excludedProposition CONTAINS X </i>, then return <i>(zero , 1.)</i>,
     * that is a full rejection,<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; - Otherwise, return <i>(X AND cocomplement(excludedProposition)
     * , 1.)</i>, that is <i>X</i> truncated by <i>excludedProposition</i>.
     * <BR><BR>
     * Except for the referee function creation, this example works like
     * {@link RefereeToolbox_Tutorial#RefereeFuserRTS_Comparison() }, so that the
     * subsequent explained code only concern the Referee Function creation.
     *
     * <BR><BR>
     * <b>Detailed code description.</b><BR>
     * The following typonomic conventions are used:<BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Blue color is used for commenting the following code.
     * </font><BR>
     * code formating is used for printing the code.<BR>
     * </code>
     * <BR>
     * Commented code:
     * <BR><BR>
     * <code>
     * <font color="#0000FF" style="font-family: georgia">
     * Creation of a class <i>myRefereeFunction</i> extending the class
     * {@link RefereeFunctionDempster } (a referee function for Dempster-Shafer).
     * This definition is generic, depending on a class <i>Prop</i> which is a complemented lattice,
     * that is {@code Prop extends ComplementedLattice<Prop> };
     * </font><BR>
     * class {@code myRefereeFunction<Prop extends ComplementedLattice<Prop> > }<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
     * {@code extends RefereeFunctionDempster<Prop> }{<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Declaration of the excluded proposition <i>excludedProposition</i>. By
     * default, it is undefined;
     * </font><BR>
     * &nbsp; &nbsp; public Prop excludedProposition = null;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Redefinition (overriding) of method {@code ArrayList<Assignment<Prop>> refereeFunction(...) };
     * </font><BR>
     * &nbsp; &nbsp; @Override<BR>
     * &nbsp; &nbsp; public {@code ArrayList<Assignment<Prop>> }refereeFunction(<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
     * {@code ArrayList<Assignment<Prop>> assignIn, }<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
     * {@code ArrayList<minAssignment<Prop>>  bbaIn) }{<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * In case <i>excludedProposition</i> is undefined, do like the referee function
     * for Dempster-Shafer;
     * </font><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; if(excludedProposition==null) return
     * super.refereeFunction(assignIn, bbaIn);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Otherwise, compute the referee function for Dempster-Shafer and store the
     * result within <i>output</i> ;
     * </font><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; {@code ArrayList<Assignment<Prop>> }
     * output=super.refereeFunction(assignIn, bbaIn);<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * If the attribute of <i>output</i> is contained by <i>excludedProposition</i>
     * </font><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; if(excludedProposition.contains(output.get(0).attribute))<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Then, set <i>output</i> to a rejection;
     * </font><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; output.get(0).attribute.zero();<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Otherwise, set <i>output</i> to its conjunction with <i>cocomplement(excludedProposition)</i>;
     * </font><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; else output.get(0).attribute.and(output.get(0).attribute,<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
     * excludedProposition.clone().cocomplement());<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * At last, return <i>output</i>;
     * </font><BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; return output;<BR>
     * &nbsp; &nbsp; }<BR>
     * <BR>
     * }<BR>
     *<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * <b>Typical usage:</b>
     * <BR><BR>
     * Create a referee function <i>myReferee</i> typed <i>myRefereeFunction</i>
     * and instantiated for powerset;
     * </font><BR>
     * {@code myRefereeFunction<finalPowerset> myReferee = }<BR>
     * &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
     * {@code new myRefereeFunction<finalPowerset>(); }<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Set the excluded proposition to proposition <i>A</i>;
     * </font><BR>
     * myReferee.excludedProposition=A;<BR>
     * <font color="#0000FF" style="font-family: georgia">
     * Do the fusion of <i>aFuser1</i> and <i>aFuser2</i> into <i>aFuser</i>;
     * </font><BR>
     * aFuser.fuse(aFuser1,aFuser2,myReferee);
     * </code>
     *
     */
    public void Create_RefereeFunction() {

        class myRefereeFunction<Prop extends ComplementedLattice<Prop> >
                        extends RefereeFunctionDempster<Prop> {
            public Prop excludedProposition = null;

            @Override
            public ArrayList<Assignment<Prop>> refereeFunction(
                                    ArrayList<Assignment<Prop>> assignIn,
                                    ArrayList<minAssignment<Prop>>  bbaIn) {
                if(excludedProposition==null) return super.refereeFunction(assignIn, bbaIn);
                ArrayList<Assignment<Prop>> output=super.refereeFunction(assignIn, bbaIn);
                if(excludedProposition.contains(output.get(0).attribute))
                    output.get(0).attribute.zero();
                else output.get(0).attribute.and(output.get(0).attribute,
                                    excludedProposition.clone().cocomplement());
                return output;
            }

        }

        finalPowerset A=new finalPowerset();
        A.size(3); A.atomic(0);
        finalPowerset B=A.instanceNsize(); B.atomic(1);
        finalPowerset C=A.instanceNsize(); C.atomic(2);
        //
        finalPowerset AUB=A.instanceNsize(); AUB.or(A,B);
        finalPowerset BUC=A.instanceNsize(); BUC.or(B,C);
        finalPowerset CUA=A.instanceNsize(); CUA.or(C,A);
        //
        finalPowerset zero=A.instanceNsize(); zero.zero();
        finalPowerset one=A.instanceNsize(); one.one();
        //
        //
        myRefereeFunction<finalPowerset> myReferee =
                            new myRefereeFunction<finalPowerset>();
        myReferee.excludedProposition=A;
        //
        //
        finalRefereeFuserRTS_Powerset aFuser1 = new finalRefereeFuserRTS_Powerset();
        aFuser1.add(A,0.09);
        aFuser1.add(B,0.2);
        aFuser1.add(C,0.02);
        aFuser1.add(AUB,0.05);
        aFuser1.add(BUC,0.03);
        aFuser1.add(CUA,0.1);
        aFuser1.add(A,0.11);
        aFuser1.add(one,0.4);
        //
        finalRefereeFuserRTS_Powerset aFuser2 = aFuser1.instance();
        aFuser2.add(A,0.1);
        aFuser2.add(B,0.1);
        aFuser2.add(C,0.2);
        aFuser2.add(AUB,0.2);
        aFuser2.add(BUC,0.1);
        aFuser2.add(CUA,0.1);
        aFuser2.add(one,0.2);
        //
        finalRefereeFuserRTS_Powerset aFuser = aFuser1.instance();
//

        int printMode=1;

        System.out.println(
                "/////////////////////////////\n" +
                "// Relaxed Method\n" +
                "////////////////////\n");

        System.out.println("aFuser1");
            System.out.println(aFuser1.state(printMode));
            System.out.println();
        System.out.println("aFuser2");
            System.out.println(aFuser2.state(printMode));
            System.out.println();
        aFuser.fuse(aFuser1,aFuser2,myReferee);
        System.out.println("aFuser - myReferee");
            System.out.println("Conflict Z = "+aFuser.conflict()+" %");
            System.out.println(aFuser.state(printMode));
            System.out.println();

    }


}
