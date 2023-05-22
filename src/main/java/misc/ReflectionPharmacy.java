package misc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import setup.DataProvider;

/**
 * Using reflection extract information(modifiers, return types, parameters, etc) about fields,
 * constructors, methods. Create object and call method using the only reflection.
 */
public class ReflectionPharmacy {

    public static void main(String[] args)
        throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        // Get the Class object for the Pharmacy class
        Class<?> classRPharmacy = Class.forName("pharmacy.Pharmacy");

        // Extract field information
        Field[] fields = classRPharmacy.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field: " + field.getName());
            System.out.println("Type: " + field.getType());
            // getModifiers return ints that represent a modifier
            System.out.println("Modifiers: " + Modifier.toString(field.getModifiers()));
            System.out.println("Modifier is public: " + Modifier.isPublic(field.getModifiers()));
            System.out.println();
        }

        // Extract constructor information
        Constructor<?>[] constructorsR = classRPharmacy.getConstructors();
        for (Constructor<?> constructorR : constructorsR) {
            System.out.println("Constructor: " + constructorR.getName());
            Parameter[] parameters = constructorR.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("Parameter: " + parameter.getName());
                System.out.println("Type: " + parameter.getType().getName());
            }
            System.out.println();
        }

        // Extract method information
        Method[] methods = classRPharmacy.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
            System.out.println("Return Type: " + method.getReturnType().getName());

            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("Parameter: " + parameter.getName());
                System.out.println("Type: " + parameter.getType().getName());
            }
            System.out.println();
        }

        // Create an instance of Pharmacy using reflection
        Constructor<?> constructor = classRPharmacy.getConstructor(String.class, Address.class,
            String.class, String.class);
        Object pharmacyR = constructor.newInstance("Reflected Pharmacy",
            DataProvider.predefinedAddresses()[0], "1234567890", "example@example.com");

        Method getNameMethod = classRPharmacy.getDeclaredMethod("getName");
        System.out.println("Before name change: " + getNameMethod.invoke(pharmacyR));

        // Access the 'name' field
        Field fieldName = classRPharmacy.getDeclaredField("name");
        fieldName.setAccessible(true);
        // Set the new value for the 'name' field
        fieldName.set(pharmacyR, "New Pharmacy Name");
        System.out.println("After name change: " + getNameMethod.invoke(pharmacyR));
        System.out.println();
    }
}
