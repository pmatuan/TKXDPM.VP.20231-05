import React, {useState, useEffect} from 'react';
import ReactDatetimeClass from "react-datetime";
import 'react-datetime/css/react-datetime.css';

interface Props {
  isRushDelivery: boolean;
  setRushDelivery: (value: boolean) => void;
  setRushDeliveryTime: (value: Date) => void;
  setRushDeliveryInstructions: (value: string) => void;
}

export default function OrderSummary({
                                       isRushDelivery,
                                       setRushDelivery,
                                       setRushDeliveryTime,
                                       setRushDeliveryInstructions
                                     }: Props) {
  const handleCheckboxChange = (event) => {
    setRushDelivery(event.target.checked);
  };

  const handleDatetimeChange = (event) => {
    setRushDeliveryTime(event.toDate());
  };
  return (
      <>
        <div className="flex items-center">
          <input
              type="checkbox"
              checked={isRushDelivery}
              onChange={handleCheckboxChange}
              className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
          />
          <label>Giao hàng nhanh</label>
        </div>

        {isRushDelivery && (
            <div className="row">
              <div className="col-4">
                <div className="form-group">
                  <label>Thời gian nhận hàng</label>
                  {/*<input type="text" className="form-control" placeholder="Thời gian nhận hàng" onChange={(e) => setDeliveryTime(e.target.value)}/>*/}
                  <ReactDatetimeClass locale="vn" initialValue={new Date()} input={true} className="appearance-none" onChange={handleDatetimeChange}/>
                </div>
              </div>

              <div className="col-8">
                <div className="form-group">
                  <label>Chỉ dẫn giao hàng</label>
                  <input type="text" className="form-control" placeholder="Chỉ dẫn giao hàng"
                         onChange={(e) => setRushDeliveryInstructions(e.target.value)}/>
                </div>
              </div>
            </div>
        )}
      </>
  );
}
