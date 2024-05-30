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

import org.checkerframework.checker.units.qual.C;
import org.sonar.check.Rule;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.SubscriptionContext;
import org.sonar.plugins.python.api.tree.CallExpression;
import org.sonar.plugins.python.api.tree.Decorator;
import org.sonar.plugins.python.api.tree.FunctionDef;
import org.sonar.plugins.python.api.tree.Name;
import org.sonar.plugins.python.api.tree.RegularArgument;
import org.sonar.plugins.python.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

@Rule(key = "EC1337")
public class AvoidUnlimitedCache extends PythonSubscriptionCheck {

    public static final String DESCRIPTION = "Do not set cache size to unlimited";

    public static final String LRU_CACHE = "lru_cache";
    public static final String MAX_SIZE_ARGUMENT = "maxsize";


    public static final String CACHE = "cache";

    @Override
    public void initialize(Context context) {
        // Check function decorators
        context.registerSyntaxNodeConsumer(Tree.Kind.FUNCDEF, this::check_function);
    }

    private void check_function(SubscriptionContext ctx) {
        FunctionDef function = (FunctionDef) ctx.syntaxNode();

        function.decorators().forEach(decorator -> {
            // If decorator is @cache
            if (is_cache_decorator(decorator)) {
                ctx.addIssue(decorator, AvoidUnlimitedCache.DESCRIPTION);
            // If decorator is @lru_cache
            }  else if (is_lru_cache_decorator(decorator)) {
                if (decorator.arguments() != null) {
                    decorator.arguments().arguments().forEach(arg -> {
                        RegularArgument regArg = (RegularArgument) arg;
                        if (MAX_SIZE_ARGUMENT.equals(regArg.keywordArgument().name()) && regArg.expression().is(Tree.Kind.NONE)) {
                            ctx.addIssue(decorator, AvoidUnlimitedCache.DESCRIPTION);
                        }
                    });
                }
            }
        });
    }

    private boolean is_cache_decorator(Decorator decorator) {
        return is_decorator(decorator, CACHE);
    }

    private boolean is_lru_cache_decorator(Decorator decorator) {
        return is_decorator(decorator, LRU_CACHE);
    }

    private boolean is_decorator(Decorator decorator, String expression) {
        Name name = null;
        // Manage decarator detected as simple expression
        if (decorator.expression().is(Tree.Kind.NAME)) {
            name = (Name) decorator.expression();
        // manage decorator detected as callable expression
        } else if(decorator.expression().is(Tree.Kind.CALL_EXPR)) {
            CallExpression callExpression = (CallExpression) decorator.expression();
            if (callExpression.callee().is(Tree.Kind.NAME)) {
                name = (Name) callExpression.callee();
            }
        }
        return name != null && expression.equals(name.name());
    }
}
