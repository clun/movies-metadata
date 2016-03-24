package fr.clunven.docu.web.domain;

/**
 * Manifest.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class DocuManifest {
	
	/** build time. */
	private String buildTime;
	
	/** version. */
	private String version;

	/**
	 * Getter accessor for attribute 'buildTime'.
	 *
	 * @return
	 *       current value of 'buildTime'
	 */
	public String getBuildTime() {
		return buildTime;
	}

	/**
	 * Setter accessor for attribute 'buildTime'.
	 * @param buildTime
	 * 		new value for 'buildTime '
	 */
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	/**
	 * Getter accessor for attribute 'version'.
	 *
	 * @return
	 *       current value of 'version'
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Setter accessor for attribute 'version'.
	 * @param version
	 * 		new value for 'version '
	 */
	public void setVersion(String version) {
		this.version = version;
	}

}
