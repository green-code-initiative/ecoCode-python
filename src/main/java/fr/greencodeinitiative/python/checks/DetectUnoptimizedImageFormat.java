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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Rule(key = "EC203")
public class DetectUnoptimizedImageFormat extends PythonSubscriptionCheck {

    protected static final String RULE_KEY = "EC203";
    protected static final String MESSAGERULE = "Detect unoptimized image format";
    protected static final String MESSAGEERROR = "If possible, the utilisation of svg image format (or <svg/> html tag) is recommended over other image format.";
    protected static final Pattern IMGEXTENSION = Pattern.compile("\\.(bmp|ico|tiff|webp|png|jpg|jpeg|jfif|pjpeg|pjp|gif|avif|apng)");

    @Override
    public void initialize(Context context) {
        context.registerSyntaxNodeConsumer(Tree.Kind.STRING_LITERAL, this::visitNodeString);
    }

    public void visitNodeString(SubscriptionContext ctx) {
        if (ctx.syntaxNode().is(Tree.Kind.STRING_LITERAL)) {
            final  StringLiteral stringLiteral = (StringLiteral) ctx.syntaxNode();
            final String strValue = stringLiteral.trimmedQuotesValue();
            final Matcher matcher = IMGEXTENSION.matcher(strValue);
            if(matcher.find()) {
                ctx.addIssue(stringLiteral, MESSAGEERROR);
            }
        }
    }
}
