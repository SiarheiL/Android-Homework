package com.example.slezgouka.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by slezgouka on 2/4/2015.
 */
public class CalculatorActivity extends Activity {

    private final int EMPTY_OPERATORS = 0;
    private final int SECOND_OPERAND_0 = 1;

    EditText mFirstOperand;
    EditText mSecondOperand;
    RadioGroup mOperator;
    TextView mResult;
    Button mPerformButton;
    Button mViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Toast.makeText(this, savedInstanceState.getCharSequence("result"), Toast.LENGTH_LONG).show();
        }

        setContentView(R.layout.layout_calc);

        mFirstOperand = (EditText) findViewById(R.id.first_number);
        mSecondOperand = (EditText) findViewById(R.id.second_number);
        mOperator = (RadioGroup) findViewById(R.id.radio_group);
        mResult = (TextView) findViewById(R.id.results);

        mPerformButton = (Button) findViewById(R.id.perform_operation);
        mPerformButton.setOnClickListener(new OnPerformOperationListener());

        mViewHistory  = (Button) findViewById(R.id.history_list);
        mViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("result", mResult.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mResult.setText(savedInstanceState.getCharSequence("result"));
    }



    private class OnPerformOperationListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            double result;
            int checkedRadioBtn = mOperator.getCheckedRadioButtonId();
            try {
                if (TextUtils.isEmpty(mFirstOperand.getText()) || TextUtils.isEmpty(mSecondOperand.getText())) {
                    throw new IllegalArgumentException();
                }
                switch (checkedRadioBtn) {
                    case R.id.plus_operation:
                        result = Double.parseDouble(mFirstOperand.getText().toString()) +
                                Double.parseDouble(mSecondOperand.getText().toString());
                        setResult(result);
                        break;
                    case R.id.minus_operation:
                        result = Double.parseDouble(mFirstOperand.getText().toString()) -
                                Double.parseDouble(mSecondOperand.getText().toString());
                        setResult(result);
                        break;
                    case R.id.multiply_operation:
                        result = Double.parseDouble(mFirstOperand.getText().toString()) *
                                Double.parseDouble(mSecondOperand.getText().toString());
                        setResult(result);
                        break;
                    case R.id.divide_operation:

                        if (Double.parseDouble(mSecondOperand.getText().toString()) == 0) {
                            throw new IllegalArgumentException();
                        } else {
                            result = Double.parseDouble(mFirstOperand.getText().toString()) /
                                    Double.parseDouble(mSecondOperand.getText().toString());
                            setResult(result);
                        }

                        break;
                    default:
                        showCalculateDialog(EMPTY_OPERATORS);
                        Toast.makeText(CalculatorActivity.this, R.string.msg_operator_not_select,
                                Toast.LENGTH_SHORT).show();
                }
            } catch (IllegalArgumentException e) {
                showCalculateDialog(SECOND_OPERAND_0);
                Toast.makeText(
                        CalculatorActivity.this, R.string.msg_illegal_operand, Toast.LENGTH_SHORT
                ).show();
            }

        }

        private void setResult(double result) {
            mResult.setText(getString(R.string.result_format, result));
            HistoryStorage.getInstance().addHistoryElement(mResult.getText().toString());
        }

    }

    protected void showCalculateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (id) {
            case EMPTY_OPERATORS:
                builder.setTitle("Dialog")
                        .setMessage(R.string.dlg_msg_incorrect_operand)
                        .setCancelable(false)
                        .setPositiveButton(R.string.dlg_msg_on_OK_btn,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

            case SECOND_OPERAND_0:
                builder.setTitle("Dialog")
                        .setMessage(R.string.dlg_msg_operator_empty)
                        .setCancelable(false)
                        .setPositiveButton(R.string.dlg_msg_on_OK_btn,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
            default:
                builder.setTitle("Dialog")
                        .setMessage(R.string.dlg_msg_default)
                        .setCancelable(false)
                        .setPositiveButton(R.string.dlg_msg_on_OK_btn,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
        }
        AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();
    }
}
