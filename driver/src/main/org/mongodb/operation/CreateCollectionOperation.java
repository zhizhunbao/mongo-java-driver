/*
 * Copyright (c) 2008-2014 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.operation;

import org.mongodb.CreateCollectionOptions;
import org.mongodb.MongoFuture;
import org.mongodb.binding.WriteBinding;
import org.mongodb.session.Session;

import static org.mongodb.assertions.Assertions.notNull;
import static org.mongodb.operation.OperationHelper.executeWrappedCommandProtocol;
import static org.mongodb.operation.OperationHelper.executeWrappedCommandProtocolAsync;
import static org.mongodb.operation.OperationHelper.ignoreResult;

public class CreateCollectionOperation implements AsyncOperation<Void>, WriteOperation<Void> {
    private final String databaseName;
    private final CreateCollectionOptions createCollectionOptions;

    public CreateCollectionOperation(final String databaseName, final CreateCollectionOptions createCollectionOptions) {
        this.databaseName = notNull("databaseName", databaseName);
        this.createCollectionOptions = notNull("createCollectionOptions", createCollectionOptions);
    }

    @Override
    public Void execute(final WriteBinding binding) {
        executeWrappedCommandProtocol(databaseName, createCollectionOptions.asDocument(), binding);
        return null;
    }

    @Override
    public MongoFuture<Void> executeAsync(final Session session) {
        return ignoreResult(executeWrappedCommandProtocolAsync(databaseName, createCollectionOptions.asDocument(), session));
    }

}