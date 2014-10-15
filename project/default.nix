{ sbt ? import ../sbt.nix {}, deps ? import ../deps.nix { inherit sbt; } }:
let

in sbt.mkDerivation {
  pname = "my-foo";
  version = "0.1.0-SNAPSHOT";
  src = ./.;
  sources = [ ./src/main/scala ];
  scalacOptions = "";
  buildDepends = [
    deps.commons-io_commons-io_2_4
  ];
  meta = {
    description = "my-foo";
  };
}