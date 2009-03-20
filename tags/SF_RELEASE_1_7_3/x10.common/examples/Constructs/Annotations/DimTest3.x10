//OPTIONS: -PLUGINS=dims.plugin.DimensionTypePlugin

import harness.x10Test;
import x10.lang.*;
import dims.*;

public class DimTest_MustFailCompile extends harness.x10Test {
    public boolean run() {
    	// This test case simulates the failure of the Mars Climate Orbiter.
    	
// The Mars Climate Orbiter�s reaction wheels were kept within their linear
// (unsaturated) range through thruster firings in a procedure called Angular
// Momentum Desaturation (AMD). When an AMD event occurred, relevant spacecraft
// data was telemetered to the ground, processed, and placed into a file called
// the AMD file. The JPL operations navigation team used data derived from the
// AMD file to model the forces on the spacecraft resulting from these specific
// thruster firings. Modeling of these small forces is critical for accurately
// determining the spacecraft�s trajectory. Immediately after the thruster
// firing, the velocity change ("delta-V") is computed using the firing time for
// each of the thrusters, and an impulse bit, which models each thruster's
// performance. The calculation of the thruster performance is carried out both
// on-board the spacecraft and on ground support computers. The flight software
// installed on the spacecraft correctly computed the velocity change and
// transmitted it to earth. The ground software, however, was originally written
// for the Mars Global Surveyor (MGS) mission, and the MGS flight software did
// not compute nor transmit velocity change information. The ground software,
// then, discarded the transmitted velocity change and recomputed it. Since the
// Mars Climate orbiter used a differently-sized thruster than Mars Global
// Surveyor, an update to the thruster equation in the ground software was
// necessary. The conversion factor from pound-seconds to newton-seconds was
// buried in the original equation and not immediately identifiable, and so it
// was not included in the updated equation. Thus, the ground software reported
// calculated "impulse bits" which were a factor of 4.45 too large (1 pound
// force = 4.45 newtons). Subsequent processing of the calculated impulse bit
// values from the AMD file by the navigation software underestimated the effect
// of the thruster firings on the spacecraft trajectory by this factor.
	
    	double @Unit(Force.lbf) model = 1.0;
    	double @Unit(Force.N) actual = (@Unit(Force.N)) model;
        double diff = ((double) actual * 4.45) - (double) model;
        return -0.001 <= diff && diff <= 0.001;
    }

    public static void main(String[] args) {
        new DimTest_MustFailCompile().execute();
    }
}
