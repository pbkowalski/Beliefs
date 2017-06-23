package knowledgebase;

import java.util.HashMap;

import knowledgebase.evidencepropertyfunction.EvidencePropertyFunction;

public class EvidencePropositionMap extends KnowledgeMap<EvidenceProperty, EvidencePropertyFunction>{
	public EvidencePropositionMap(){
		this.setMap(new HashMap<EvidenceProperty, EvidencePropertyFunction>());
	}
}
