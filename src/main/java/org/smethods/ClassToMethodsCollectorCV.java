package org.smethods;

import org.ekstazi.asm.ClassVisitor;
import org.ekstazi.asm.MethodVisitor;
import org.ekstazi.asm.Opcodes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.smethods.Macros.PROJECT_PACKAGE;

public class ClassToMethodsCollectorCV extends ClassVisitor {

    // Name of the class being visited.
    private String mClassName;

    Map<String, Set<String>> class2ContainedMethodNames;
    Map<String, Set<String>> hierarchy_parents;
    Map<String, Set<String>> hierarchy_children;

    public ClassToMethodsCollectorCV(Map<String, Set<String>> class2ContainedMethodNames,
            Map<String, Set<String>> hierarchy_parents,
            Map<String, Set<String>> hierarchy_children) {
        super(Opcodes.ASM5);
        this.class2ContainedMethodNames = class2ContainedMethodNames;
        this.hierarchy_parents = hierarchy_parents;
        this.hierarchy_children = hierarchy_children;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        mClassName = name;
        Set<String> parents = hierarchy_parents.getOrDefault(name, new HashSet<>());
        if (superName != null && !superName.startsWith("java/") && !superName.startsWith("org/junit/")
                && !superName.startsWith("org/smethods/") && !superName.startsWith("org/ekstazi/")) {
            parents.add(superName);
            Set<String> subClasses = hierarchy_children.getOrDefault(superName, new HashSet<>());
            subClasses.add(name);
            hierarchy_children.put(superName, subClasses);
        }
        for (String i : interfaces) {
            parents.add(i);
            Set<String> subClasses = hierarchy_children.getOrDefault(i, new HashSet<>());
            subClasses.add(name);
            hierarchy_children.put(i, subClasses);
        }
        hierarchy_parents.put(name, parents);
    }

    @Override
    public MethodVisitor visitMethod(int access, final String outerName, final String outerDesc, String signature,
            String[] exceptions) {
        // append arguments to key, remove what after ) of desc
        if (outerName.equals(PROJECT_PACKAGE)) {
            return null;
        }
        String m = outerName + outerDesc.substring(0, outerDesc.indexOf(")") + 1);
        Set<String> methods = class2ContainedMethodNames.getOrDefault(mClassName, new TreeSet<>());
        methods.add(m);
        class2ContainedMethodNames.put(mClassName, methods);
        return null;
    }

}