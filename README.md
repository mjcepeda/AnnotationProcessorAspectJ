
# AnnotationProcessorAspectJ
Hello world project using AspectJ and annotation processor.

For this example, I followed the next tutorial:
http://andrewclement.blogspot.de/2014/08/annotation-processing-in-ajdt.html

Similar to annotation-processor-sample project. In this case we create an aspect file using annotation processor for every method annotated with @StreamsFactory.

This project only contains the main class, the interface class @StreamsFactory, and the processor StreamProcessor, are in processor.jar in the lib folder. 

For this example, @StreamsFactory is identical to the interface in annotation-processor-sample project, but the target is ElementType.METHOD instead of ElementType.TYPE.

The class StreamProcessor is also similar to that one in annotation-processor, but in this case, the process method creates an aspect file for every method annotated with @StreamsFactory. That aspect has an before advice for that method that injects just a simple message.

This is the code snipped:

for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(StreamsFactory.class)) {
			// Check if a method has been annotated with @StreamsFactory
			if (annotatedElement.getKind() != ElementKind.METHOD) {
				error(annotatedElement, "Only methods can be annotated with @%s", StreamsFactory.class.getSimpleName());
				return true; // Exit processing
			}

			// For any methods we find, create an aspect:
	        String methodName = annotatedElement.getSimpleName().toString();
	        String aspectText = 
	          "public aspect Advise_"+methodName+" {\n"+
	          "  before(): execution(* "+methodName+"(..)) {\n"+
	          "    System.out.println(\""+methodName+" running\");\n"+
	          "  }\n"+
	          "}\n";
	        try {
	          JavaFileObject file = filer.createSourceFile("Advise_"+methodName, annotatedElement);
	          file.openWriter().append(aspectText).close();
	          System.out.println("Generated aspect to advise "+methodName);
	        } catch (IOException ioe) {
	        }
		}

