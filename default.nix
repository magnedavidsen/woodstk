{ sbt ? import ./sbt.nix {}, deps ? import ./deps.nix { inherit sbt; } }:
{
  project = sbt.callPackage ./project {};

}