package executables;
import RefereeToolbox.RFDempster_Powerset;
import beliefs.Proposition;
import beliefs.RandomSet;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class vastFusion {
    public static void main(String[] args) throws Exception {


        Proposition PD = new Proposition("PD");
        Proposition D = new Proposition("PD!");
        Proposition P = new Proposition("P!D");
        Proposition not = new Proposition("P!D!");

        TreeSet<Proposition> fodset = new TreeSet<>();
        fodset.add(P);fodset.add(PD);fodset.add(D);fodset.add(not);
        FrameOfDiscernment theta = new FrameOfDiscernment(fodset);

        Set<Proposition> focalSet1 = new HashSet<>(Arrays.asList(PD,P));
        Set<Proposition> focalSet2 = new HashSet<>(Arrays.asList(D, not));
        Set<Proposition> focalSet3 = new HashSet<>(Arrays.asList(PD, P, D, not));

        Set<Proposition> focalSet4 = new HashSet<>(Arrays.asList(PD, D));
        Set<Proposition> focalSet5 = new HashSet<>(Arrays.asList(P, not));

        MassAssignment bba1 = new MassAssignment(); //empty bba (P, extended)
        MassAssignment bba2 = new MassAssignment(); //empty bba (P, extended)
        MassAssignment bba3 = new MassAssignment();
        MassAssignment bba4 = new MassAssignment();
        MassAssignment bba5 = new MassAssignment();

        bba1.put(focalSet1, 0.8668).put(focalSet2, 0.0248).put(focalSet3, 0.1084);

        bba2.put(focalSet4, 0.7).put(focalSet3, 0.3); //prior on D

        bba3.put(focalSet1, 0).put(focalSet2, 0.0236).put(focalSet3, 0.9764); //K
        bba4.put(focalSet1, 0.8).put(focalSet2, 0).put(focalSet3, 0.2); //prior on P
        bba5.put(focalSet1, 0.4).put(focalSet2, 0.1).put(focalSet3, 0.5); //PoL


        RFDempster_Powerset referee1 = new RFDempster_Powerset();

        RandomSet rs1 = new RandomSet(theta, bba1); //joint on P
        RandomSet rs2 = new RandomSet(theta, bba2); //prior on D
        RandomSet rs3 = new RandomSet(theta, bba3);
        RandomSet rs4 = new RandomSet(theta, bba4);
        RandomSet rs5 = new RandomSet(theta, bba5);
        RandomSet fusionResult = rs1.fuseRS(rs2, referee1);
        fusionResult.getMassAssignment().print(1);

        RandomSet plausConditionedResult = fusionResult.plausibilityMass(new String[]{"PD"});

        double d1 = rs1.findDistance(plausConditionedResult);
        double d2 = rs2.findDistance(plausConditionedResult);

        double d3 = rs3.findDistance(plausConditionedResult);
        double d4 = rs4.findDistance(plausConditionedResult);
        double d5 = rs5.findDistance(plausConditionedResult);

        System.out.println("Distances to m cond on {PD}");

        System.out.print(d1);System.out.print(",");System.out.print(d2);System.out.print(",");System.out.print(d3);System.out.print(",");System.out.print(d4);System.out.print(",");System.out.print(d5);
    }
    }
