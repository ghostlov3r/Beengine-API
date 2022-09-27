package beengine.api.util;

import java.util.regex.Pattern;

/**
 * Manages version strings, and compares them
 */
public class Version implements Comparable<Version> {
	private String baseVersion;
	private String suffix;

	private int major;
	private int minor;
	private int patch;

	private int build;
	private boolean development = false;

	public Version(String baseVersion) {
		this(baseVersion, false);
	}

	public Version(String baseVersion, boolean isDevBuild) {
		this(baseVersion, isDevBuild, 0);
	}

	public Version(String baseVersion, boolean isDevBuild, int buildNumber) {
		this.baseVersion = baseVersion;
		this.development = isDevBuild;
		this.build = buildNumber;

		String[] matches = baseVersion.split(Pattern.quote("."));
		
		if (matches.length < 3) {
			throw new IllegalArgumentException("Invalid base version '"+baseVersion+"', should contain at least 3 version digits");
		}
		
		this.major = Integer.parseInt(matches[0]);
		this.minor = Integer.parseInt(matches[1]);
		this.patch = Integer.parseInt(matches[2]);
		this.suffix = matches.length >= 4 ? matches[3] : "";
		
	}

	public int getNumber () {
		return ((this.major << 9) | (this.minor << 5) | this.patch);
	}

	public String getBaseVersion () {
		return this.baseVersion;
	}

	public String getFullVersion () {
		return getFullVersion(false);
	}

	public String getFullVersion (boolean build) {
		String retval = this.baseVersion;
		if (this.development) {
			retval += "+dev";
			if (build && this.build > 0) {
				retval += "." + this.build;
			}
		}

		return retval;
	}

	public int getMajor () {
		return this.major;
	}

	public int getMinor () {
		return this.minor;
	}

	public int getPatch () {
		return this.patch;
	}

	public String getSuffix () {
		return this.suffix;
	}

	public int getBuild () {
		return this.build;
	}

	public boolean isDev () {
		return this.development;
	}

	@Override
	public String toString () {
		return this.getFullVersion();
	}
	
	@Override
	public int compareTo(Version target) {
		return compareTo(target, false);
	}
	
	public int compareTo (Version target, boolean diff) {
		int number = this.getNumber();
		int tNumber = target.getNumber();
		if (diff)
			return tNumber - number;
		if (number > tNumber)
			return -1; //Target is older
		if (number < tNumber)
			return 1; //Target is newer
		if (target.isDev() && !this.isDev())
			return -1; //Dev builds of the same version are always considered older than a release
		if (target.getBuild() > this.getBuild())
			return 1;
		if (target.getBuild() < this.getBuild())
			return -1;
		
		return 0; //Same version
	}
}