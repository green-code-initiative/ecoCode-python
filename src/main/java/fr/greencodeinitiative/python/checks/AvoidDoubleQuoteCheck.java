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
import org.sonar.plugins.python.api.SubscriptionContext;
import org.sonar.plugins.python.api.tree.StringLiteral;
import org.sonar.plugins.python.api.tree.Tree;

/**
 * @deprecated not applicable for Python
 * (check discussion inside issue https://github.com/green-code-initiative/ecoCode-python/issues/4)
 */
@Deprecated(forRemoval = true)
@Rule(key = "EC66")
public class AvoidDoubleQuoteCheck extends PythonSubscriptionCheck {
    public static final String MESSAGE_RULE = "Avoid using quotation mark (\"), prefer using simple quote (')";
    @Override
    public void initialize(Context context) {
        context.registerSyntaxNodeConsumer(Tree.Kind.STRING_LITERAL, this::visitNodeString);
    }

    private void visitNodeString(SubscriptionContext subscriptionContext) {
        StringLiteral stringLiteral = (StringLiteral) subscriptionContext.syntaxNode();

        if (!stringLiteral.stringElements().isEmpty() && stringLiteral.stringElements().get(0).value().startsWith("\"")){
            subscriptionContext.addIssue(stringLiteral, MESSAGE_RULE);
        }
    }
}
