package at.alirezamoh.idea_whisperer_for_laravel.support.psiUtil;

import at.alirezamoh.idea_whisperer_for_laravel.support.IdeaWhispererForLaravelIcon;
import at.alirezamoh.idea_whisperer_for_laravel.support.strUtil.StrUtil;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.php.lang.psi.elements.*;
import com.jetbrains.php.lang.psi.elements.impl.ArrayHashElementImpl;
import org.jetbrains.annotations.Nullable;

/**
 * Provides utility methods for working with PSI elements
 */
public class PsiUtil {
    /**
     * Builds a LookupElementBuilder for a config key
     *
     * @param key The config key
     * @return The LookupElementBuilder
     */
    public static LookupElementBuilder buildSimpleLookupElement(String key) {
        return LookupElementBuilder
            .create(key)
            .withLookupString(key)
            .bold()
            .withIcon(IdeaWhispererForLaravelIcon.LARAVEL_ICON);
    }

    /**
     * Returns the start offset of a text string, excluding any leading or trailing quotes
     * @param text The text string
     * @return     The start offset
     */
    public static int getStartOffset(String text) {
        return (text.startsWith("\"") || text.startsWith("'")) ? 1 : 0;
    }

    /**
     * Returns the end offset of a text string, excluding any leading or trailing quotes
     * @param text The text string
     * @return     The end offset
     */
    public static int getEndOffset(String text) {
        return (text.endsWith("\"") || text.startsWith("'")) ? text.length() - 1 : text.length();
    }

    /**
     * Checks if an offset is within the text range of a PSI element
     * @param firstParameter The PSI element
     * @param offset         The offset to check
     * @return               True if the offset is within the element's range, false otherwise
     */
    public static boolean isInRange(PsiElement firstParameter, int offset) {
        TextRange parameterRange = firstParameter.getTextRange();
        return parameterRange.contains(offset);
    }

    /**
     * Extracts the first parameter from a method
     * @param method method reference
     * @return the first parameter value
     */
    public static String getFirstParameterFromMethod(MethodReference method) {
        String key = "";

        ParameterList parameters = method.getParameterList();
        if (parameters != null) {
            PsiElement namespaceParameter = parameters.getParameter(0);
            if (namespaceParameter != null) {
                key = StrUtil.removeQuotes(namespaceParameter.getText());
            }
        }

        return key;
    }

    /**
     * Extracts the second parameter from a method
     * @param method method reference
     * @return the second parameter value
     */
    public static @Nullable String getSecondParameterFromMethod(MethodReference method) {
        String key = null;

        ParameterList parameters = method.getParameterList();
        if (parameters != null) {
            PsiElement namespaceParameter = parameters.getParameter(1);
            if (namespaceParameter != null) {
                key = StrUtil.removeQuotes(namespaceParameter.getText());
            }
        }

        return key;
    }

    public static boolean isInArrayValue(PsiElement element, int maxDepth) {
        PsiElement currentElement = element;
        int currentDepth = 0;

        while (currentElement != null && currentDepth < maxDepth) {
            if (currentElement instanceof ArrayHashElementImpl arrayHashElement) {
                if (arrayHashElement.getValue() == element) {
                    return true;
                }
            }

            currentElement = currentElement.getParent();
            currentDepth++;
        }

        return false;
    }

    public static boolean isAssocArray(PsiElement element) {
        PsiElement parent = element.getParent();

        if (parent != null) {
            PsiElement prevSibling = parent.getPrevSibling();

            if (prevSibling instanceof LeafPsiElement) {
                return prevSibling.textMatches("=>");
            }
            else {
                prevSibling = parent.getNextSibling() != null ? parent.getNextSibling().getNextSibling() : null;
                return prevSibling instanceof LeafPsiElement && prevSibling.textMatches("=>");
            }
        }

        return false;
    }

    public static boolean isInRegularArray(PsiElement element, int maxDepth) {
        PsiElement currentElement = element;
        int depth = 0;

        while (currentElement != null && depth < maxDepth) {
            if (currentElement instanceof ArrayCreationExpression) {
                return true;
            }
            currentElement = currentElement.getParent();
            depth++;
        }

        return false;
    }

}
