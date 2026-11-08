# Building Power Industries

The Fission Reactor prototype targets Minecraft Forge 1.20.1 and Java 17.

From the repository root, run:

```bash
gradle build --no-daemon
```

The distributable mod JAR is produced under `build/libs/` when the build succeeds. The GitHub Actions workflow runs this build on pushes to `main` and `dev/fission-reactor` and uploads the JAR as the `javatea-mod-jar` artifact.

A successful build is not yet confirmed for this branch. Existing source references to steam/boiler classes that are absent from the repository may need to be resolved first.
