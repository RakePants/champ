import React, { useState, useCallback } from 'react';

const Input = ({image, setImage}) => {
    const [inputValue, setInputValue] = useState('');
    const convertToBase64 = (file) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          setInputValue(reader.result);
        };
        reader.onerror = (error) => {
          console.log('Error converting to base64:', error);
        };
      }

    const onDrop = useCallback((event) => {
        event.preventDefault();
        if (event.dataTransfer.files && event.dataTransfer.files[0]) {
            const file = event.dataTransfer.files[0];
            if (file.type.startsWith('image/')) {
                setImage(file);
                convertToBase64(file); // convertToBase64(file);
            }
        }
    }, []);

    const onInputClick = (event) => {
        event.target.value = null;
    };

    const onChange = (event) => {
        if (event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (file.type.startsWith('image/')) {
                console.log(file)
                setImage(file)
                convertToBase64(file);

            }
        }
    };

    return (
        <div
            onDrop={onDrop}
            onDragOver={(event) => event.preventDefault()}
            onClick={onInputClick}
            style={{
                border: '2px solid #FFA500',
                width: '400px',
                minHeight: '80px',
                margin: '0 auto',
                backgroundColor: '#232323',
                color: '#fff',
                borderRadius: '20px',
                padding: '20px',
                textAlign: 'center',
                cursor: 'pointer',
            }}
        >
            <input
                type="file"
                accept="image/*"
                style={{ display: 'none' }}
                onChange={onChange}
                onClick={onInputClick}
                id="fileInput"
            />
            <label htmlFor="fileInput" style={{ cursor: 'pointer' }}>
                Перетащите сюда изображение или щелкните, чтобы выбрать его.      </label>
            {inputValue && <img src={inputValue} alt="Preview" style={{ marginTop: '20px', maxWidth: '100%' }} />}
        </div>
    );
};

export default Input;