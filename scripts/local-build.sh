#!/bin/bash

set -e

PROJECT_ROOT=$(cd "$(dirname "$0")/.." && pwd -P)
cd "$PROJECT_ROOT"

# ./mvnw used for consistency and portability across different environments
# -DskipTests disables tests
./mvnw clean package -DskipTests
