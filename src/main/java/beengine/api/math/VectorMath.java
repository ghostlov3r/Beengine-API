package beengine.api.math;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VectorMath {

	public Vector2 getDirection2D (float azimuth) {
		return new Vector2(
			FMath.cos(azimuth),
			FMath.sin(azimuth)
		);
	}

}