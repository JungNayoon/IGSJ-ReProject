package ezen.project.IGSJ.productFile.domain;

public class ProductFileDTO {

	private String pno;
	private String originalFileName;
	private String storedFileRootName;
	private String storedThumbNailName;

	public ProductFileDTO(String pno, String originalFileName, String storedFileRootName, String storedThumbNailName) {
		this.pno = pno;
		this.originalFileName = originalFileName;
		this.storedFileRootName = storedFileRootName;
		this.storedThumbNailName = storedThumbNailName;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getStoredFileRootName() {
		return storedFileRootName;
	}

	public void setStoredFileRootName(String storedFileRootName) {
		this.storedFileRootName = storedFileRootName;
	}

	public String getStoredThumbNailName() {
		return storedThumbNailName;
	}

	public void setStoredThumbNailName(String storedThumbNailName) {
		this.storedThumbNailName = storedThumbNailName;
	}

	@Override
	public String toString() {
		return "ProductFileDTO [pno=" + pno + ", originalFileName=" + originalFileName + ", storedFileRootName=" + storedFileRootName
				+ ", storedThumbNailName=" + storedThumbNailName + "]";
	}

}
