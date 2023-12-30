// import { Field } from "react-final-form";
// import React from "react";

// const FileField = ({ name, label, ...props }) => (
//   <div className="mb-3 row">
//     <div className="col-8">
//       {/* Adjust the column width as needed */}
//       <Field name={name}>
//         {({ input: { value, onChange, ...input } }) => (
//           <div>
//             {label && (
//               <label htmlFor={name} className="form-label">
//                 {label}
//               </label>
//             )}
//             <input
//               {...input}
//               type="file"
//               accept="image/*"
//               onChange={({ target }) => onChange(target.files)}
//               className="form-control"
//               {...props}
//             />
//           </div>
//         )}
//       </Field>
//     </div>
//   </div>
// );

// export default FileField;

import React from "react";
import { Field } from "react-final-form";

const FileField = ({ name, label, validate, ...props }) => (
  <div className="mb-3 row">
    <div className="col-8">
      <Field name={name} validate={validate}>
        {({ input: { value, onChange, ...input }, meta }) => (
          <div>
            {label && (
              <label htmlFor={name} className="form-label">
                {label}
              </label>
            )}
            <input
              {...input}
              type="file"
              accept="image/*"
              onChange={({ target }) => onChange(target.files)}
              className={`form-control ${
                meta.error && meta.touched ? "is-invalid" : ""
              }`}
              {...props}
            />
            {meta.error && meta.touched && (
              <div className="invalid-feedback">{meta.error}</div>
            )}
          </div>
        )}
      </Field>
    </div>
  </div>
);

export default FileField;
