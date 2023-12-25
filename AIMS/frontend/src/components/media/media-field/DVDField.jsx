import InputField from "../custom-field/customInputField";
import RadioButtonField from "../custom-field/customRadioButtonField";

const disFormats = [
  { value: "UNKNOWN", label: "UNKNOWN" },
  { value: "BLURAY", label: "BLURAY" },
  { value: "HDDVD", label: "HDDVD" },
];
export default function DVDField() {
  return (
    <div>
      <RadioButtonField
        name="disFormat"
        options={disFormats}
        label="Disk Format"
      />
      <InputField name="directors" type="text" label="Directors" />
      <InputField name="runTime" type="number" label="Run Time" />
      <InputField name="studio" type="text" label="Studio" />
      <InputField name="languague" type="text" label="Languague" />
      <InputField name="subtitiles" type="text" label="Subtitles" />
      <InputField name="genres" type="text" label="Genres" />
      <InputField name="releaseDate" type="date" label="Release Date" />
    </div>
  );
}
