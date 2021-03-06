/*
 * Copyright 2015 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.tudelft.pds.granula.modeller.openg.job;

import nl.tudelft.pds.granula.archiver.entity.operation.Job;
import nl.tudelft.pds.granula.modeller.model.job.JobModel;
import nl.tudelft.pds.granula.modeller.openg.OpenGType;
import nl.tudelft.pds.granula.modeller.openg.operation.*;
import nl.tudelft.pds.granula.modeller.rule.derivation.DerivationRule;
import nl.tudelft.pds.granula.modeller.rule.filling.UniqueOperationFilling;
import nl.tudelft.pds.granula.modeller.rule.extraction.OpenGExtractionRule;

/**
 * Created by wing on 12-3-15.
 */
public class OpenG extends JobModel {

    public OpenG() {
        super();
        addOperationModel(new TopActorTopMission());
        addOperationModel(new OpenGJob());
        addOperationModel(new LoadGraph());
        addOperationModel(new OffloadGraph());
        addOperationModel(new ProcessGraph());

    }

    public void loadRules() {

        addFillingRule(new UniqueOperationFilling(2, OpenGType.TopActor, OpenGType.TopMission));

        addInfoDerivation(new JobNameDerivationRule(4));
        addExtraction(new OpenGExtractionRule(1));
    }


    protected class JobNameDerivationRule extends DerivationRule {

        public JobNameDerivationRule(int level) {
            super(level);
        }

        @Override
        public boolean execute() {

            Job job = (Job) entity;
//
//
//            BasicInfo jobNameInfo = new BasicInfo("JobName");
//            jobNameInfo.addInfo("unspecified", new ArrayList<Source>());
//            job.addInfo(jobNameInfo);
//
//            String jobName = null;
//            List<Operation> operations = job.findOperations(OpenGType.MRApp, OpenGType.MRJob);
//            for (Operation operation : operations) {
//                jobName = operation.getInfo("JobName").getValue();
//            }
//            if(jobName == null) {
//                throw new IllegalStateException();
//            }

            job.setName("A OpenG job");
            job.setType("OpenG");

            return true;

        }
    }
}
