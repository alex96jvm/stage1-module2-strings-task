package com.epam.mjc;

import java.util.List;

public class MethodParser {
    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] signatureArray = signatureString.split( "\\(");
        String[] signatureWithoutParametersArray = signatureArray[0].split(" ");
        String[] parametersArray = signatureArray[1].substring(0, signatureArray[1].length()-1).split(", ");

        MethodSignature methodSignature = new MethodSignature(signatureWithoutParametersArray[signatureWithoutParametersArray.length-1]);
        methodSignature.setReturnType(signatureWithoutParametersArray[signatureWithoutParametersArray.length-2]);
        methodSignature.setAccessModifier(signatureWithoutParametersArray.length == 3 ? signatureWithoutParametersArray[signatureWithoutParametersArray.length - 3] : null);

        if(!parametersArray[0].isBlank()){
            List<MethodSignature.Argument> arguments = methodSignature.getArguments();
            for (String parameter : parametersArray) {
                arguments.add(new MethodSignature.Argument(parameter.split(" ")[0], parameter.split(" ")[1]));
            }
        }
        return methodSignature;
    }
}
