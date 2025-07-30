/********************************************************************************
 * Copyright (c) 2025 Cofinity-X GmbH
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

package org.eclipse.tractusx.edc.tests.tck.dcp;

import org.eclipse.edc.policy.context.request.spi.RequestCatalogPolicyContext;
import org.eclipse.edc.policy.context.request.spi.RequestContractNegotiationPolicyContext;
import org.eclipse.edc.policy.context.request.spi.RequestTransferProcessPolicyContext;
import org.eclipse.edc.policy.context.request.spi.RequestVersionPolicyContext;
import org.eclipse.edc.policy.engine.spi.PolicyEngine;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;

import java.util.Set;

/**
 * This extension registers a default scope mapping function, which causes a particular scope to be added to <em>every</em>
 * Presentation Query
 */
public class DefaultScopeFunctionExtension implements ServiceExtension {

    @Inject
    private PolicyEngine policyEngine;

    @Override
    public void initialize(ServiceExtensionContext context) {

        // register a default scope provider
        var contextMappingFunction = new DefaultScopeMappingFunction(Set.of("org.eclipse.dspace.dcp.vc.type:MembershipCredential:read"));

        policyEngine.registerPostValidator(RequestCatalogPolicyContext.class, contextMappingFunction::apply);
        policyEngine.registerPostValidator(RequestContractNegotiationPolicyContext.class, contextMappingFunction::apply);
        policyEngine.registerPostValidator(RequestTransferProcessPolicyContext.class, contextMappingFunction::apply);
        policyEngine.registerPostValidator(RequestVersionPolicyContext.class, contextMappingFunction::apply);

    }
}
