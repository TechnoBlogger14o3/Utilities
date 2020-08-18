# Utilities (US Mobile Number Format (541) 754-3010)


```
 public static void addTextWatcherForPhoneNoUS(final EditText edtView) {
        edtView.addTextChangedListener(new TextWatcher() {
            // We need to know if the user is erasing or entering some new character.
            private boolean backspacingFlag = false;
            // We need to block the :afterTextChanges method to be called again after we just replaced the EditText text.
            private boolean editedFlag = false;
            // We need to mark the cursor position and restore it after the edition.
            private int cursorComplement;

            /**
             * This method is called to notify you that, within "s",
             * the count characters beginning at start
             * are about to be replaced by new text with length after.
             * It is an error to attempt to make changes to s from
             * this callback.
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // We store the cursor local relative to the end of the string in the EditText before the edition
                cursorComplement = s.length() - edtView.getSelectionStart();
                // We check if the user is entering or erasing a character
                backspacingFlag = count > after;
            }

            /**
             * This method is called to notify you that, within "s",
             * the count characters beginning at start
             * have just replaced old text that had length before.
             * It is an error to attempt to make changes to s from
             * this callback.
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            /**
             * This method is called to notify you that, somewhere within
             * "s", the text has been changed.
             * It is legitimate to make further changes to "s" from
             * this callback, but be careful not to get yourself into an infinite
             * loop, because any changes you make will cause this method to be
             * called again recursively.
             * (You are not told where the change took place because other
             * afterTextChanged() methods may already have made other changes
             * and invalidated the offsets.
             */
            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                // What matters are the phone digits beneath the mask, so we always work with a raw string with only digits.
                String phone = string.replaceAll("[^\\d]", "");

                // If the text was just edited, :afterTextChanged is called another time... so we need to verify the flag of edition.
                // If the flag is false, this is a original user-typed entry. so we go on and do some magic.
                if (!editedFlag) {
                    // We start verifying the worst case, many characters mask need to be added.
                    if (phone.length() >= 6 && !backspacingFlag) {
                        // We will edit. next call on this textWatcher will be ignored.
                        editedFlag = true;
                        // Here is the core. we substring the raw digits and add the mask as convenient.
                        String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6);
                        edtView.setText(ans);
                        // We deliver the cursor to its original position relative to the end of the string.
                        edtView.setSelection(edtView.getText().length() - cursorComplement);
                        // We end at the most simple case, when just one character mask is needed.
                    } else if (phone.length() >= 3 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3);
                        edtView.setText(ans);
                        edtView.setSelection(edtView.getText().length() - cursorComplement);
                    }
                    // We just edited the field, ignoring this cicle of the watcher and getting ready for the next.
                } else {
                    editedFlag = false;
                }
            }
        });    
```

