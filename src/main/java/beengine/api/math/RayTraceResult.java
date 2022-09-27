package beengine.api.math;

import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Class representing a ray trace collision with an AxisAlignedBB
 */
@Value
@Accessors(fluent = true)
public class RayTraceResult {
	AxisAlignedBB boundingBox;
	Facing hitFace;
	Vector3 hitVector;
}