# Fission Reactor prototype

This feature is being developed on the `dev/fission-reactor` branch for the repository's existing Forge 1.20.1 / Java 17 setup.

## Imported artwork

The block textures in `assets/javatea/textures/block` are copied byte-for-byte from the user's `Mlosokm/Art-drawing` repository. The original drawing repository is not modified.

Included artwork:
- Reactor casing
- Controller OFF / ON / RUNNING states
- FRA unfilled / filled sides and top-bottom sheet
- RRC side / top / bottom
- Input / output / waste-output ports

## Registered blocks

- Fission Reactor Casing
- Reactor Fuel Rod Assembly (FRA), with a filled visual state
- Reactor Rod Controller Assembly (RRC)
- Reactor Input Port
- Reactor Output Port
- Reactor Waste Output Port
- Fission Reactor Controller

## Current controller prototype

Right-click the controller once to enable it. Right-click again to validate the nearby casing structure. If validation succeeds, the controller changes to its RUNNING texture. Right-click again to stop it.

Validation currently checks:
- A connected casing/port shell with a rectangular outer boundary
- At least one FRA column inside the shell
- No gaps inside an FRA column
- One RRC directly above each FRA column
- No RRC blocks in unrelated interior positions
- FRA and RRC assemblies remain inside the casing

Feedback is shown in-world rather than in a missing-parts GUI:
- Amber dust marks an expected/missing position
- Red dust marks an incorrect assembly position

The particle feedback is a first-pass temporary visual indicator. A persistent outline renderer can replace it later without changing the validator.

## Not implemented yet

This is the first structural prototype, not a complete reactor simulation. Port fluid behavior, fuel logic, heat/power behavior, persistence, and a final multiblock size/shape policy remain future work. The current validator infers the casing bounds from the connected shell and should be tested against the intended build layout before gameplay use.
