/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jetbrains.buildServer.runner.codedeploy;

import jetbrains.buildServer.ExtensionHolder;
import jetbrains.buildServer.serverSide.problems.BaseBuildProblemTypeDetailsProvider;
import jetbrains.buildServer.serverSide.problems.BuildProblemTypeDetailsProvider;
import org.jetbrains.annotations.NotNull;

/**
 * @author vbedrosova
 */
public class CodeDeployBuildProblemTypes {

  public CodeDeployBuildProblemTypes(@NotNull ExtensionHolder extensionHolder) {
    register(CodeDeployConstants.TIMEOUT_BUILD_PROBLEM_TYPE, "CodeDeploy timeout", extensionHolder);
    register(CodeDeployConstants.FAILURE_BUILD_PROBLEM_TYPE, "CodeDeploy failure", extensionHolder);
    register(CodeDeployConstants.EXCEPTION_BUILD_PROBLEM_TYPE, "AWS unexpected exception", extensionHolder);
    register(CodeDeployConstants.SERVICE_PROBLEM_TYPE, "AWS service exception", extensionHolder);
    register(CodeDeployConstants.CLIENT_PROBLEM_TYPE, "AWS client exception", extensionHolder);
  }

  private static void register(@NotNull final String type, @NotNull final String descr, @NotNull final ExtensionHolder extensionHolder) {
    extensionHolder.registerExtension(BuildProblemTypeDetailsProvider.class,
      type,
      new BaseBuildProblemTypeDetailsProvider() {
        @NotNull
        public String getType() {
          return type;
        }

        @NotNull
        @Override
        public String getTypeDescription() {
          return descr;
        }
      });
  }

}
