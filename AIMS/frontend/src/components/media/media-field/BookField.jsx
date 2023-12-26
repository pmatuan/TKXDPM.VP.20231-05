import RadioButtonField from "../custom-field/customRadioButtonField.jsx";
import InputField from "../custom-field/customInputField.jsx";

const coverTypes = [
  { value: "UNKNOWN", label: "UNKNOWN" },
  { value: "PAPERBACK", label: "PAPERBACK" },
  { value: "HARDCOVER", label: "HARDCOVER" },
];

export default function BookField() {
  return (
    <div>
      <InputField name="authors" type="text" label="Authors" />
      <RadioButtonField
        name="coverType"
        options={coverTypes}
        label="Cover Type "
      />
      <InputField name="publisher" type="text" label="Publisher" />
      <InputField name="publicationDate" type="date" label="Publication Date" />
      <InputField name="pages" type="number" label="Pages" />
      <InputField name="language" type="text" label="Language" />
      <InputField name="type" type="text" label="Type" />
    </div>
  );
}
