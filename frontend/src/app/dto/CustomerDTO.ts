export interface CustomerDTO {
    id: number;
    firstName: String,
    lastName: string,
    phone: string,
    email: string,
    street: string,
    city: string,
    state: string,
    zipCode: string,
    createdAt: Date | string | null,
    updatedAt: Date | string | null,
}