# functional RULES : please see HTML description file of this rule (resources directory)

# ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
# ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
#
# COMPLIANT use cases
#
# ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
# ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

# COMPLIANT
# USE CASE : compliant use case to check if a variable is used maximum twice on several IF / ELSE statements
# at the same level AND no problem with several IF staments at the same level using different variables
def shouldBeCompliantBecauseVariablesUsedMaximumTwiceAndDifferentsVariablesUsed():
    nb1 = 0
    nb2 = 0
    nb3 = 0
    if nb3 != 1 and nb1 > 1 :
        nb1 = 1
    else :
        nb2 = 2
    if nb2 == 2 :
        nb1 = 3
    else :
        nb1 = 4
    return nb1

# COMPLIANT
# USE CASE : compliant use case to check if a variable is used maximum twice on several IF / ELSE statements
# at the same level AND no problem with several IF staments at the same level using different variables
def shouldBeCompliantBecauseVariablesUsedMaximumTwiceAndDifferentsVariablesUsedAtDiffLevels():
    nb1 = 0
    nb2 = 0
    nb3 = 0
    if nb1 < 1:
        if nb2 == 2:
            nb3 = 3
        else:
            nb3 = 4
    else:
        nb2 = 2
    if nb3 >= 1:
        if nb2 == 2:
            nb1 = 3
        else:
            nb1 = 4
    else:
        nb1 = 2
    return nb1

# COMPLIANT
# USE CASE : compliant use case to check if a variable is used maximum twice on several IF / ELSE statements
# at the same level AND no problem with several IF staments at the same level using different variables
def shouldBeCompliantBecauseVariablesUsedMaximumTwiceAndDiffVariablesUsedAtDiffLevelsScenario2():
    nb1 = 0
    nb2 = 0
    nb3 = 0
    if nb1 <= 1:
        if nb2 == 2:
            if nb3 == 2:
                nb3 = 3
            else:
                nb3 = 4
        else:
            nb3 = 4
    else:
        nb2 = 2
    if nb3 == 1:
        if nb2 == 2:
            nb1 = 3
        else:
            nb1 = 4
    else:
        nb1 = 2
    return nb1

# COMPLIANT
# USE CASE : compliant use case to check if one variable is used maximum twice in different IF statements
def shouldBeCompliantBecauseVariableUsedMaximumTwiceInIfStatements():
    nb1 = 0
    if nb1 == 1:
        nb1 = 1
    if nb1 == 2:
        nb1 = 3
    return nb1

# COMPLIANT
# USE CASE : compliant use case to check if following is OK :
#  - two uses of the same variable
#  - usage of the same variable on different levels of IF statements
def shouldBeCompliantBecauseSereralVariablesUsedMaximumTwiceInComposedElseStatements():
    nb1 = 0
    nb2 = 0
    nb3 = 0
    if nb1 == 1:
        nb1 = 2
    else:
        if nb2 == 2:
            nb1 = 1
        else:
            if nb3 == 4:
                nb1 = 3
            else:
                nb1 = 6
    return nb1

# COMPLIANT
# USE CASE : compliant use case to check if following is OK :
#  - two uses of the same variable
#  - usage of the same variable on different kind of test statements (IF and elif)
def shouldBeCompliantBecauseVariableUsedMaximumTwiceInIfOrelifStatements():
    nb1 = 0
    nb2 = 10
    if nb1 == 1:
        nb2 = 1
    elif nb1 == nb2:
        nb2 = 2
    return nb2

# COMPLIANT
# USE CASE : compliant use case to check if following is OK :
#  - two uses of the same variable
#  - usage of the same variable on different kind of test statements (IF and elif)
def shouldBeCompliantBecauseSeveralVariablesUsedMaximumTwiceInIfOrelifStatements():
    nb1 = 0
    nb2 = 10
    nb3 = 3
    nb4 = 1
    nb5 = 2
    if nb1 == 1:
        nb2 = 1
    elif nb3 == nb2:
        nb2 = 2
    elif nb4 == nb5:
        nb2 = 4
    else:
        nb2 = 3
    return nb2
