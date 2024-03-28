import os

def create_directories() -> None:
    directories = [
        "src/main/java/common",
        "src/main/java/core",
        "src/main/java/utils",
        "src/main/resources",
        "src/test/java/common",
        "src/test/java/core",
        "src/test/java/utils",
        "src/test/resources"
    ]

    base_path = os.path.dirname(os.path.abspath(__file__))

    for directory in directories:
        dir_path = os.path.join(base_path, directory)
        os.makedirs(dir_path, exist_ok=True)

    created_directories = [os.path.join(dp, f) for dp, dn, filenames in os.walk(base_path) for f in dn]
    print("Created directories:")
    for directory in created_directories:
        print(f'\t{directory}')



def create_main() -> None:

    base_path = os.path.dirname(os.path.abspath(__file__))

    directory_path = os.path.join(base_path, "src/main/java")
    file_name = 'Main.java'
    file_path = os.path.join(directory_path, file_name)

    with open(file_path, 'w') as file:
        file.write('package main.java;\n\npublic class Main {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println("Hello world!");\n\t}\n}')

    return file_path

    print("Created main file:")
    print(f'\t{file_path}')


def overwrite_first_iml_file():
    directory = os.path.dirname(os.path.abspath(__file__))
    new_content = """<?xml version="1.0" encoding="UTF-8"?>
<module type="JAVA_MODULE" version="4">
  <component name="NewModuleRootManager" inherit-compiler-output="true">
    <exclude-output />
    <content url="file://$MODULE_DIR$">
        <sourceFolder url="file://$MODULE_DIR$/src/main/java" isTestSource="false" />
        <sourceFolder url="file://$MODULE_DIR$/src/main/resources" type="java-resource" />
        <sourceFolder url="file://$MODULE_DIR$/src/test/java" isTestSource="true" />
        <sourceFolder url="file://$MODULE_DIR$/src/test/resources" type="java-test-resource" />
    </content>
    <orderEntry type="inheritedJdk" />
    <orderEntry type="sourceFolder" forTests="false" />
    <orderEntry type="module-library">
      <library name="JUnit4">
        <CLASSES>
          <root url="jar://$MAVEN_REPOSITORY$/junit/junit/4.13.1/junit-4.13.1.jar!/" />
          <root url="jar://$MAVEN_REPOSITORY$/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar!/" />
        </CLASSES>
        <JAVADOC />
        <SOURCES />
      </library>
    </orderEntry>
  </component>
</module>
    """


    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.iml'):
                iml_file_path = os.path.join(root, file)
                with open(iml_file_path, 'w') as iml_file:
                    iml_file.write(new_content)
                print(f"Content written to {iml_file_path}")
                return
    print("No .iml file found.")



def main() -> None:
    create_directories()
    # create_main()
    overwrite_first_iml_file()

if __name__ == '__main__':
    main()