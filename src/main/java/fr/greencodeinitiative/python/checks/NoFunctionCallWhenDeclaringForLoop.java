/*
 * ecoCode - Python language - Provides rules to reduce the environmental footprint of your Python programs
 * Copyright © 2023 Green Code Initiative (https://www.ecocode.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package fr.greencodeinitiative.python.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.tree.CallExpression;
import org.sonar.plugins.python.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * @deprecated not applicable for Python
 * (check discussion inside issue https://github.com/green-code-initiative/ecoCode-python/issues/21)
 */
@Deprecated(forRemoval = true)
@Rule(key = "EC69")
@DeprecatedRuleKey(repositoryKey = "gci-python", ruleKey = "S69")
public class NoFunctionCallWhenDeclaringForLoop extends PythonSubscriptionCheck {

    public static final String DESCRIPTION = "Do not call a function when declaring a for-type loop";

    @Override
    public void initialize(Context context) {
        context.registerSyntaxNodeConsumer(Tree.Kind.CALL_EXPR, ctx -> {
            CallExpression callExpression = (CallExpression) ctx.syntaxNode();
            if (callExpression.parent().getKind() == Tree.Kind.FOR_STMT) {
                ctx.addIssue(callExpression, NoFunctionCallWhenDeclaringForLoop.DESCRIPTION);
            }
        });
    }
}