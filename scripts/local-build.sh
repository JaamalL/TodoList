#!/bin/bash

set -e

PROJECT_ROOT=$(cd "$(dirname "$0")/.." && pwd -P)
cd "$PROJECT_ROOT"

mvn clean package
