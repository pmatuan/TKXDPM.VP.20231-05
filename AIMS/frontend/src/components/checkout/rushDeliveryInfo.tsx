import React, { useState, useEffect } from 'react';

export default function OrderSummary() {
  const [isFastDeliveryChecked, setIsFastDeliveryChecked] = useState(false);

  const handleCheckboxChange = (event) => {
    setIsFastDeliveryChecked(event.target.checked);
  };

  return (
      <>
        <div className="flex items-center">
          <input
              type="checkbox"
              checked={isFastDeliveryChecked}
              onChange={handleCheckboxChange}
              className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
          />
          <label>Giao hàng nhanh</label>
        </div>

        {isFastDeliveryChecked && (
            <div className="row">
              <div className="col-4">
                <div className="form-group">
                  <label>Thời gian nhận hàng</label>
                  <input type="text" className="form-control" placeholder="Thời gian nhận hàng"/>
                </div>
              </div>

              <div className="col-8">
                <div className="form-group">
                  <label>Chỉ dẫn giao hàng</label>
                  <input type="text" className="form-control" placeholder="Chỉ dẫn giao hàng"/>
                </div>
              </div>
            </div>
        )}
      </>
  );
}
