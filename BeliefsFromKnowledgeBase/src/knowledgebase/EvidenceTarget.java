package knowledgebase;

public class EvidenceTarget{
	private String targetName;
	public EvidenceTarget(String name){
		this.setTargetName(name);
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
}
